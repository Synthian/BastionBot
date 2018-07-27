package ic.bastionbot;

import ic.bastionbot.speedruncom.SpeedrunManager;
import ic.bastionbot.twitch.TwitchManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BastionBot {

    private static final Logger logger = LoggerFactory.getLogger(BastionBot.class.getName());

    private Discord discord;
    private Instant last_tick_sr;
    private SpeedrunManager speedrun;
    private TwitchManager twitch;
    private ChatListener chatListener;
    private final ScheduledExecutorService srses, twses;

    public BastionBot() {

        //instantiate discord object
        discord = new Discord(Constants.TOKEN);

        //add chat message listener
        chatListener = new ChatListener();
        discord.addEventListener(chatListener);

        //set up Speedrun
        speedrun = new SpeedrunManager(discord);

        //set up Twitch
        twitch = new TwitchManager(discord);

        //set the initial timestamp
        //When we pull the leaderboards from speedrun.com, we only analyze runs that have taken place since our last timestamp
        last_tick_sr = Instant.now();

        //start the speedrun service
        srses = Executors.newSingleThreadScheduledExecutor();
        srses.scheduleWithFixedDelay(() -> {
            speedrun.checkForNewRuns(last_tick_sr);
            last_tick_sr = Instant.now();
        }, 0, Constants.SR_REFRESHRATE, TimeUnit.SECONDS);

        //start the twitch service
        twses = Executors.newSingleThreadScheduledExecutor();
        twses.scheduleWithFixedDelay(() -> twitch.checkForLiveStreams(), Constants.TW_REFRESHRATE, Constants.TW_REFRESHRATE, TimeUnit.SECONDS);

        logger.info("BastionBot initialization complete.");
    }

    public void sendServerMessage(String msg, String channelName) {
        TextChannel targetChannel = discord.getTextChannelsByName(discord.getGuildById(Constants.PROD_GUILDID), channelName).get(0);
        MessageBuilder mb = new MessageBuilder();
        mb.append(msg);
        discord.queueSendMessage(mb.build(), targetChannel);
    }

    public void quit() {
        twses.shutdown();
        srses.shutdown();
        discord.disconnect();
    }

}

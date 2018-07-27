package ic.bastionbot.twitch;


import ic.bastionbot.Constants;
import ic.bastionbot.Discord;
import ic.bastionbot.twitch.objects.StreamsResponse;
import ic.bastionbot.twitch.objects.components.Stream;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitchManager {

    private static final Logger logger = LoggerFactory.getLogger(TwitchManager.class.getName());

    private TwitchAPI twapi;
    private Discord discord;
    private TextChannel targetChannel;
    private long laststream;
    private List<LiveStream> livestreams;

    public TwitchManager(Discord discord) {
        this.discord = discord;
        this.twapi = new TwitchAPI();
        targetChannel = discord.getTextChannelsByName(discord.getGuildById(Constants.PROD_GUILDID), Constants.STREAM_CHANNEL).get(0);
        livestreams = new LinkedList<>();
        laststream = 0;
    }

    public void checkForLiveStreams() {
        twapi.fetchLiveStreams(Constants.TARGET_GAME, "live",  new Callback<StreamsResponse>() {
            @Override
            public void onResponse(Call<StreamsResponse> call, Response<StreamsResponse> response) {

                if (response.body() != null) {
                    // get all of the streams we need to check
                    //noinspection ConstantConditions
                    List<Stream> streams = response.body().getStreams();

                    for (Stream s : streams) {
                        //If it is a speedrun stream
                        if (s.getCommunityId().equals(Constants.SR_COMMUNITY_ID) ||
                                s.getChannel().getStatus().toUpperCase().contains("ASL") ||
                                s.getChannel().getStatus().toUpperCase().contains("SPEEDRUN") ||
                                s.getChannel().getStatus().contains("%")) {
                            //if it is already in the livestream list
                            if (!isAlreadyLive(s.getId())) {
                                Message newmessage;
                                //if it was not the last stream to be posted about
                                if (s.getChannel().getId() != laststream) {
                                    logger.info("Creating new Twitch text post and embed.");
                                    logger.info("channel id: " + s.getChannel().getId());
                                    logger.info("stream id: " + s.getId());
                                    logger.info("channel name: " + s.getChannel().getDisplayName());
                                    laststream = s.getChannel().getId();
                                    newmessage = createTextPost(s);
                                    createEmbedPost(s);
                                } else {
                                    logger.info("Creating repeat Twitch text post.");
                                    logger.info("channel id: " + s.getChannel().getId());
                                    logger.info("stream id: " + s.getId());
                                    logger.info("channel name: " + s.getChannel().getDisplayName());
                                    newmessage = createRepeatPost(s);
                                }
                                livestreams.add(new LiveStream(s.getId(), newmessage));
                            }
                        }
                    }

                    //extract list of live stream Ids rather than stream objects
                    List<Long> liveIds = streams.stream().map(s -> s.getId()).collect(Collectors.toList());

                    //check the list for each stream we have a post for
                    //act appropriately when it disappears
                    for (LiveStream ls : livestreams) {
                        if (!liveIds.contains(ls.getId())) {
                            logger.info("Editing Twitch text post to offline.");
                            logger.info("stream id:" + ls.getId());
                            editTextPost(ls.getMessage());
                            livestreams.remove(ls);
                        }
                    }
                }
                else {
                    logger.warn("checkForLiveStreams response is null");
                }
            }

            @Override
            public void onFailure(Call<StreamsResponse> call, Throwable throwable) {
                logger.warn("checkForLiveStreams call FAILED");
            }
        });

        // ** Deprecated method of stream offlining **

        /*for (LiveStream ls : livestreams) {
            twapi.fetchChannelStream(ls.getId(), new Callback<StreamResponse>() {
                @Override
                public void onResponse(Call<StreamResponse> call, Response<StreamResponse> response) {
                    if (response.body() != null) {
                        if (response.body().getStream() == null) {
                            editTextPost(ls.getMessage());

                            ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
                            ses.schedule((Runnable) () -> livestreams.remove(ls), 30, TimeUnit.MINUTES);
                        }
                    }
                    else {
                        //null response body
                    }
                }

                @Override
                public void onFailure(Call<StreamResponse> call, Throwable throwable) {
                    //onresponse error
                }
            });
        }
        */
    }

    private void createEmbedPost(Stream s) {
        EmbedBuilder eb = new EmbedBuilder();

        String profile_img;
        if (s.getChannel().getLogo() == null)
            profile_img = Constants.RUN_ICON;
        else {
            profile_img = s.getChannel().getLogo();
            eb.setThumbnail(s.getChannel().getLogo());
        }

        eb.setAuthor(s.getChannel().getDisplayName(), s.getChannel().getUrl(), profile_img);
        eb.setTitle(s.getChannel().getStatus(), s.getChannel().getUrl());
        eb.setDescription(s.getChannel().getDescription());
        eb.setFooter("Twitch", Constants.TWITCH_ICON);
        eb.setColor(new Color(45, 80, 135));

        discord.queueSendEmbed(eb.build(), targetChannel);
    }

    private Message createTextPost(Stream s) {
        MessageBuilder mb = new MessageBuilder();

        mb.append("**").append(s.getChannel().getDisplayName()).append("**");
        mb.append(" is streaming a Bastion speedrun at ");
        mb.append("<").append(s.getChannel().getUrl()).append(">");

        return discord.sendMessageBlocking(mb.build(), targetChannel);
    }

    private void editTextPost(Message m) {
        MessageBuilder mb = new MessageBuilder();
        mb.append(m.getContentRaw());
        mb.append(" *").append("(This stream has ended)").append("*");

        discord.queueEditMessage(m, mb.build());
    }

    private Message createRepeatPost(Stream s) {
        MessageBuilder mb = new MessageBuilder();

        mb.append(s.getChannel().getDisplayName());
        mb.append(" is streaming another Bastion speedrun!");

        return discord.sendMessageBlocking(mb.build(), targetChannel);
    }

    private boolean isAlreadyLive(long id) {
        for (LiveStream ls : livestreams) {
            if (ls.getId() == id)
                return true;
        }
        return false;
    }

}
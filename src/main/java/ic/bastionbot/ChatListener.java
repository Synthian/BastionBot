package ic.bastionbot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ChatListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ChatListener.class.getName());

    public ChatListener() {

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();

        //Pull useful data from the received message
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String textMessage = message.getContentDisplay();

        //If sent in a normal text channel
        if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();
            String name = member.getEffectiveName();

            if (textMessage.startsWith("!talkforaspell")) {
                logger.info("Sending !talkforaspell response.");

                MessageBuilder mb = new MessageBuilder();

                mb.append("https://clips.twitch.tv/SingleGeniusAlfalfaTebowing");

                Message response = mb.build();
                textChannel.sendMessage(response).queue();
            }
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        MessageReaction.ReactionEmote reaction = event.getReactionEmote();
        Emote emote = reaction.getEmote();
        long messageId = event.getMessageIdLong();
        MessageChannel channel = event.getChannel();
        Message message = channel.getMessageById(messageId).complete();

        Guild guild = event.getGuild();

        List<Emote> goldEmotes = guild.getEmotesByName("Gold", false);
        if (!goldEmotes.isEmpty()) {
            if (emote == goldEmotes.get(0)) {
                logger.info("Mimicking Gold emote reaction.");
                message.addReaction(goldEmotes.get(0)).queue();
            }
        }
    }
}

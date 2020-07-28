package com.iancordle.bb.listeners;

import com.iancordle.bb.config.DiscordProps;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {

    private final DiscordProps discordProps;

    public ReactionListener(DiscordProps discordProps) {
        this.discordProps = discordProps;
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (discordProps.getEchoedEmojis().contains(event.getReactionEmote().getName())) {
            String messageId = event.getMessageId();
            MessageChannel c = event.getChannel();
            if (event.getReactionEmote().isEmote()) {
                c.addReactionById(messageId, event.getReactionEmote().getEmote()).complete();
            } else {
                c.addReactionById(messageId, event.getReactionEmote().getEmoji()).complete();
            }
        }
    }

}

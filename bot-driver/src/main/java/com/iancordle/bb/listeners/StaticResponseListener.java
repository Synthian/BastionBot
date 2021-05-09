package com.iancordle.bb.listeners;

import com.iancordle.bb.config.DiscordProps;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StaticResponseListener extends ListenerAdapter {

    private final DiscordProps discordProps;

    public StaticResponseListener(DiscordProps discordProps) {
        this.discordProps = discordProps;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageText = event.getMessage().getContentRaw();
        if (messageText.length() < 1 || messageText.charAt(0) != '!') return;
        int blankIndex = messageText.indexOf(' ');
        String command = messageText.substring(1, blankIndex > 0 ? blankIndex : messageText.length());

        if (discordProps.getStaticCommands().containsKey(command)) {
            event.getChannel().sendMessage(discordProps.getStaticCommands().get(command)).complete();
        }
    }
}

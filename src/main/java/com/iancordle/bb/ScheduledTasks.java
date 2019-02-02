package com.iancordle.bb;

import net.dv8tion.jda.core.entities.TextChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final TextChannel messageTC;

    public ScheduledTasks(@Qualifier("MessageTextChannel") TextChannel messageTC) {
        this.messageTC = messageTC;
    }

    @Scheduled(fixedDelayString = "${bot.refresh}")
    public void sendMessage() {
        messageTC.sendMessage("New Test!").complete();
    }

}

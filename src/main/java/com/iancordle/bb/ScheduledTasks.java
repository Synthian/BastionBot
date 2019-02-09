package com.iancordle.bb;

import com.iancordle.bb.speedrun.SpeedrunClient;
import com.iancordle.bb.speedrun.model.Run;
import com.iancordle.bb.speedrun.requests.RunsQueryParams;
import feign.FeignException;
import net.dv8tion.jda.core.entities.TextChannel;
import org.jetbrains.annotations.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    private final TextChannel messageTC;
    private final SpeedrunClient speedrunClient;

    public ScheduledTasks(@Qualifier("MessageTextChannel") TextChannel messageTC, SpeedrunClient speedrunClient) {
        this.messageTC = messageTC;
        this.speedrunClient = speedrunClient;
    }

    @Scheduled(fixedDelayString = "${bot.refresh}")
    public void sendMessage() {
        try {
            List<Run> runs = speedrunClient.getRuns(new RunsQueryParams()
                    .withGame("n268p5dp")
                    .withStatus("verified")
                    .withOrderby("verify-date")
                    .withDirection("desc"))
                    .getData();
            LOG.info("Success!");
        } catch (FeignException ex) {
            LOG.warn("[" + ex.status() + "] " + ex.contentUTF8());
        }
    }

}

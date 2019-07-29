package com.iancordle.bb.tasks;

import com.iancordle.bb.config.DiscordProps;
import com.iancordle.bb.config.SpeedrunProps;
import com.iancordle.bb.config.StyleProps;
import com.iancordle.bb.exceptions.NoPlaceException;
import com.iancordle.bb.service.SpeedrunService;
import com.iancordle.bb.speedrun.model.Run;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Component
public class SpeedrunTasks {
    private static final Logger LOG = LoggerFactory.getLogger(SpeedrunTasks.class);

    private final SpeedrunService speedrunService;
    private final TextChannel runsTextChannel;
    private final SpeedrunProps speedrunProps;
    private final StyleProps styleProps;

    private ZonedDateTime lastRun = ZonedDateTime.now();

    @Autowired
    public SpeedrunTasks(SpeedrunService speedrunService, TextChannel runsTextChannel, SpeedrunProps speedrunProps, DiscordProps discordProps, StyleProps styleProps) {
        this.speedrunService = speedrunService;
        this.runsTextChannel = runsTextChannel;
        this.speedrunProps = speedrunProps;
        this.styleProps = styleProps;
    }

    @Scheduled(fixedDelayString = "${speedrun.refresh}")
    public void processRuns() {
        List<Run> recentRuns = speedrunService.getRunsSince(lastRun);
        LOG.debug("Processing " + recentRuns.size() + " runs that have occurred since " + lastRun.toString());
        for (Run run : recentRuns) {
            try {
                int place = speedrunService.getPlaceOf(run);
                postRun(run, place);
            } catch (NoPlaceException ex) {
                LOG.warn("No place provided for run, will not generate a post.", ex);
            }
            lastRun = run.getStatus().getVerifyDate().isAfter(lastRun) ? run.getStatus().getVerifyDate() : lastRun;
        }
    }

    private void postRun(Run run, int place) {
        LOG.info("Generating run post. Run ID: " + null);

        try {
            MessageBuilder messageBuilder = new MessageBuilder();
            messageBuilder.appendFormat("**%s's** run has been verified: <%s>",
                    run.getPlayers().get(0).getNames().getInternational(),
                    run.getWeblink());

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(run.getPlayers().get(0).getNames().getInternational(), run.getPlayers().get(0).getWeblink(), styleProps.getRunIcon());
            embedBuilder.setColor(new Color(styleProps.getRunPostColor().getR(),styleProps.getRunPostColor().getG(),styleProps.getRunPostColor().getB()));
            embedBuilder.addField("Place", ordinalOf(place), true);
            embedBuilder.addField("Category", run.getCategory().getName(), true);
            embedBuilder.addField("Mode", modeFrom(run.getValues()), true);
            embedBuilder.addField("Time", formatDuration(run.getTimes().getPrimary()), true);
            embedBuilder.addField("Run Page", run.getWeblink(), false);
            embedBuilder.addField("Video Link", run.getVideos().getLinks().get(0).getUri(), false);

            runsTextChannel.sendMessage(messageBuilder.build()).complete();
            runsTextChannel.sendMessage(embedBuilder.build()).complete();
        } catch (NullPointerException npe) {
            LOG.error("Run has an unexpected null field.", npe);
        }
    }

    private String ordinalOf(int i) {
        int hundredRemainder = i % 100;
        int tenRemainder = i % 10;
        if (hundredRemainder - tenRemainder == 10) {
            return i + "th";
        }
        switch (tenRemainder) {
            case 1:
                return i + "st";
            case 2:
                return i + "nd";
            case 3:
                return i + "rd";
            default:
                return i + "th";
        }
    }

    private String modeFrom(Map<String, String> variables) {
        if (CollectionUtils.containsAny(variables.values(), speedrunProps.getNewGameVariables())) {
            return "New Game";
        } else if (CollectionUtils.containsAny(variables.values(), speedrunProps.getNewGamePlusVariables())) {
            return "New Game+";
        }
        return "Unknown";
    }

    private String formatDuration(Duration duration) {
        String isoDuration = duration.toString();
        return isoDuration.replaceAll("[PTS]","")
                .replaceAll("[HM]", ":")
                .replaceAll("\\.[0-9]+", "");
    }
}

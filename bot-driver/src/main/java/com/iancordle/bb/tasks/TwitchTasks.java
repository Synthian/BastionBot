package com.iancordle.bb.tasks;

import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.helix.domain.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.iancordle.bb.config.StyleProps;
import com.iancordle.bb.config.TwitchProps;
import com.iancordle.bb.exceptions.UserNotFoundException;
import com.iancordle.bb.service.TwitchService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TwitchTasks {
    private static final Logger LOG = LoggerFactory.getLogger(TwitchTasks.class);

    private final TwitchService twitchService;
    private final Map<String, Message> activePosts = new HashMap<>();
    private final Cache<String, Instant> lastPostByUser = CacheBuilder.newBuilder().maximumSize(100).build();
    private final TextChannel streamsTextChannel;
    private final StyleProps styleProps;
    private final TwitchProps twitchProps;

    @Autowired
    public TwitchTasks(TwitchService twitchService, TextChannel streamsTextChannel, StyleProps styleProps, TwitchProps twitchProps) {
        this.twitchService = twitchService;
        this.streamsTextChannel = streamsTextChannel;
        this.styleProps = styleProps;
        this.twitchProps = twitchProps;
    }

    @Scheduled(fixedDelayString = "${twitch.refresh}")
    public void processStreams() {
        List<Stream> streams = twitchService.getStreams();
        LOG.debug("Processing {} live streams", streams.size());
        editFinishedStreams(streams);
        postNewStreams(streams);
    }

    private void editFinishedStreams(List<Stream> streams) {
        List<String> streamIds = streams.stream().map(Stream::getId).collect(Collectors.toList());
        for (String id : activePosts.keySet()) {
            if (!streamIds.contains(id)) {
                Message m = activePosts.get(id);
                m.editMessageFormat("%s *(This stream has ended)*", m.getContentRaw()).complete();
                activePosts.remove(id);
            }
        }
    }

    private void postNewStreams(List<Stream> streams) {
        for (Stream s : streams) {
            if (!isKnownStream(s.getId()) && isSpeedrun(s) && playerOffCooldown(s.getUserId())) {
                LOG.info("Generating stream post. Stream ID: {}", s.getId());

                Message streamMessage = postStreamText(s);
                try {
                    User user = twitchService.getUser(s.getUserId());
                    postStreamEmbed(s, user);
                } catch (UserNotFoundException ex) {
                    postStreamEmbed(s, null);
                }

                activePosts.put(s.getId(), streamMessage);
                lastPostByUser.put(s.getUserId(), Instant.now());
            }
        }
    }

    private boolean isKnownStream(String id) {
        return activePosts.containsKey(id);
    }

    private boolean playerOffCooldown(String user) {
        Instant now = Instant.now();
        Instant lastPost = lastPostByUser.getIfPresent(user);
        if (lastPost == null) return true;
        return Duration.between(lastPost, now).toHours() > 3;
    }

    private boolean isSpeedrun(Stream s) {
        String title = s.getTitle().toLowerCase();
        for (String key : twitchProps.getTitleKeys()) {
            if (title.contains(key.toLowerCase())) return true;
        }
        if (s.getTagIds() != null) {
            for (UUID tag : twitchProps.getStreamTags()) {
                if (s.getTagIds().contains(tag)) return true;
            }
        }
        return false;
    }

    private Message postStreamText(Stream s) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendFormat("**%s** is streaming a Bastion speedrun: <https://twitch.tv/%s>",
                s.getUserName(),
                s.getUserName().toLowerCase());

        return streamsTextChannel.sendMessage(messageBuilder.build()).complete();
    }

    private void postStreamEmbed(Stream s, User u) {
        EmbedBuilder eb = new EmbedBuilder();

        String profile_img;
        String description;
        if (u == null || u.getProfileImageUrl() == null || u.getProfileImageUrl().isBlank()) {
            profile_img = styleProps.getRunIcon();
            description = "";
        } else {
            profile_img = u.getProfileImageUrl();
            description = u.getDescription();
        }

        String url = String.format("https://twitch.tv/%s", s.getUserName());

        eb.setAuthor(s.getUserName(), url, profile_img);
        eb.setTitle(s.getTitle(), url);
        eb.setDescription(description);
        eb.setThumbnail(s.getThumbnailUrl(800, 450));
        eb.setFooter("Twitch", styleProps.getTwitchIcon());
        eb.setColor(new Color(styleProps.getStreamPostColor().getR(),
                styleProps.getStreamPostColor().getG(),
                styleProps.getStreamPostColor().getB()));

        streamsTextChannel.sendMessage(eb.build()).complete();
    }
}

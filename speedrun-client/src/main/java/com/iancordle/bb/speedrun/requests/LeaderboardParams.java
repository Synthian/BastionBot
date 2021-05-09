package com.iancordle.bb.speedrun.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class LeaderboardParams {
    private Integer top;
    private String platform;
    private String region;
    private Boolean emulators;
    @JsonProperty("video-only")
    private Boolean videoOnly;
    private String timing;
    private ZonedDateTime date;
    // Embed
    private String embed;

    public String getEmbed() {
        return embed;
    }

    public LeaderboardParams withEmbeds(String embed) {
        this.embed = embed;
        return this;
    }

    public Integer getTop() {
        return top;
    }

    public LeaderboardParams withTop(Integer top) {
        this.top = top;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public LeaderboardParams withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public LeaderboardParams withRegion(String region) {
        this.region = region;
        return this;
    }

    public Boolean isEmulators() {
        return emulators;
    }

    public LeaderboardParams withEmulators(Boolean emulators) {
        this.emulators = emulators;
        return this;
    }

    public Boolean isVideoOnly() {
        return videoOnly;
    }

    public LeaderboardParams withVideoOnly(Boolean videoOnly) {
        this.videoOnly = videoOnly;
        return this;
    }

    public String getTiming() {
        return timing;
    }

    public LeaderboardParams withTiming(String timing) {
        this.timing = timing;
        return this;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public LeaderboardParams withDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }
}

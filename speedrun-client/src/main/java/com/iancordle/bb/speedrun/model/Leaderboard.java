package com.iancordle.bb.speedrun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Leaderboard {
    private String weblink;
    private Game game;
    private Category category;
    private Level level;
    private String Region;
    private boolean emulators;
    @JsonProperty("video-only")
    private boolean videoOnly;
    private String timing;
    private Map<String, String> values;
    private List<LeaderboardRun> runs;
    private List<Link> links;

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public boolean isEmulators() {
        return emulators;
    }

    public void setEmulators(boolean emulators) {
        this.emulators = emulators;
    }

    public boolean isVideoOnly() {
        return videoOnly;
    }

    public void setVideoOnly(boolean videoOnly) {
        this.videoOnly = videoOnly;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public List<LeaderboardRun> getRuns() {
        return runs;
    }

    public void setRuns(List<LeaderboardRun> runs) {
        this.runs = runs;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

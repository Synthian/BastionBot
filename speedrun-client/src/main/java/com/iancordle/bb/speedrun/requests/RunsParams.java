package com.iancordle.bb.speedrun.requests;

import java.util.List;

public class RunsParams {

    private String user;
    private String guest;
    private String examiner;
    private String game;
    private String level;
    private String category;
    private String platform;
    private String region;
    private Boolean emulated;
    private String status;
    // Sorting
    private String orderby;
    private String direction;
    // Embed
    private List<String> embed;

    public String getUser() {
        return user;
    }

    public RunsParams withUser(String user) {
        this.user = user;
        return this;
    }

    public String getGuest() {
        return guest;
    }

    public RunsParams withGuest(String guest) {
        this.guest = guest;
        return this;
    }

    public String getExaminer() {
        return examiner;
    }

    public RunsParams withExaminer(String examiner) {
        this.examiner = examiner;
        return this;
    }

    public String getGame() {
        return game;
    }

    public RunsParams withGame(String game) {
        this.game = game;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public RunsParams withLevel(String level) {
        this.level = level;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RunsParams withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public RunsParams withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public RunsParams withRegion(String region) {
        this.region = region;
        return this;
    }

    public Boolean getEmulated() {
        return emulated;
    }

    public RunsParams withEmulated(Boolean emulated) {
        this.emulated = emulated;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RunsParams withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOrderby() {
        return orderby;
    }

    public RunsParams withOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public RunsParams withDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public List<String> getEmbed() {
        return embed;
    }

    public RunsParams withEmbeds(List<String> embeds) {
        this.embed = embeds;
        return this;
    }
}

package com.iancordle.bb.speedrun.requests;

public class RunsQueryParams {

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

    public String getUser() {
        return user;
    }

    public RunsQueryParams withUser(String user) {
        this.user = user;
        return this;
    }

    public String getGuest() {
        return guest;
    }

    public RunsQueryParams withGuest(String guest) {
        this.guest = guest;
        return this;
    }

    public String getExaminer() {
        return examiner;
    }

    public RunsQueryParams withExaminer(String examiner) {
        this.examiner = examiner;
        return this;
    }

    public String getGame() {
        return game;
    }

    public RunsQueryParams withGame(String game) {
        this.game = game;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public RunsQueryParams withLevel(String level) {
        this.level = level;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RunsQueryParams withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public RunsQueryParams withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public RunsQueryParams withRegion(String region) {
        this.region = region;
        return this;
    }

    public Boolean getEmulated() {
        return emulated;
    }

    public RunsQueryParams withEmulated(Boolean emulated) {
        this.emulated = emulated;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RunsQueryParams withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOrderby() {
        return orderby;
    }

    public RunsQueryParams withOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public RunsQueryParams withDirection(String direction) {
        this.direction = direction;
        return this;
    }
}

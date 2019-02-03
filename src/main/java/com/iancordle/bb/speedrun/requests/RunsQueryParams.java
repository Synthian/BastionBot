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

    public String withUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String withGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String withExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public String withGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String withLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String withCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String withPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String withRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean withEmulated() {
        return emulated;
    }

    public void setEmulated(Boolean emulated) {
        this.emulated = emulated;
    }

    public String withStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String withOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String withDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }




}

package com.iancordle.bb.speedrun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Ruleset {

    @JsonProperty("show-milliseconds")
    private Boolean showMilliseconds;
    @JsonProperty("require-verification")
    private Boolean requireVerification;
    @JsonProperty("require-video")
    private Boolean requireVideo;
    @JsonProperty("run-times")
    private List<String> runTimes;
    @JsonProperty("default-time")
    private String defaultTime;
    @JsonProperty("emulators-allowed")
    private Boolean emulatorsAllowed;

    public Boolean getShowMilliseconds() {
        return showMilliseconds;
    }

    public void setShowMilliseconds(Boolean showMilliseconds) {
        this.showMilliseconds = showMilliseconds;
    }

    public Boolean getRequireVerification() {
        return requireVerification;
    }

    public void setRequireVerification(Boolean requireVerification) {
        this.requireVerification = requireVerification;
    }

    public Boolean getRequireVideo() {
        return requireVideo;
    }

    public void setRequireVideo(Boolean requireVideo) {
        this.requireVideo = requireVideo;
    }

    public List<String> getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(List<String> runTimes) {
        this.runTimes = runTimes;
    }

    public String getDefaultTime() {
        return defaultTime;
    }

    public void setDefaultTime(String defaultTime) {
        this.defaultTime = defaultTime;
    }

    public Boolean getEmulatorsAllowed() {
        return emulatorsAllowed;
    }

    public void setEmulatorsAllowed(Boolean emulatorsAllowed) {
        this.emulatorsAllowed = emulatorsAllowed;
    }
}

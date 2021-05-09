package com.iancordle.bb.speedrun.model;

import java.time.Duration;

public class Times {
    private Duration primary;
    private Integer primary_t;
    private Duration realtime;
    private Integer realtime_t;
    private Duration realtime_noloads;
    private Integer realtime_noloads_t;
    private Duration ingame;
    private Integer ingame_t;

    public Duration getPrimary() {
        return primary;
    }

    public void setPrimary(Duration primary) {
        this.primary = primary;
    }

    public Integer getPrimary_t() {
        return primary_t;
    }

    public void setPrimary_t(Integer primary_t) {
        this.primary_t = primary_t;
    }

    public Duration getRealtime() {
        return realtime;
    }

    public void setRealtime(Duration realtime) {
        this.realtime = realtime;
    }

    public Integer getRealtime_t() {
        return realtime_t;
    }

    public void setRealtime_t(Integer realtime_t) {
        this.realtime_t = realtime_t;
    }

    public Duration getRealtime_noloads() {
        return realtime_noloads;
    }

    public void setRealtime_noloads(Duration realtime_noloads) {
        this.realtime_noloads = realtime_noloads;
    }

    public Integer getRealtime_noloads_t() {
        return realtime_noloads_t;
    }

    public void setRealtime_noloads_t(Integer realtime_noloads_t) {
        this.realtime_noloads_t = realtime_noloads_t;
    }

    public Duration getIngame() {
        return ingame;
    }

    public void setIngame(Duration ingame) {
        this.ingame = ingame;
    }

    public Integer getIngame_t() {
        return ingame_t;
    }

    public void setIngame_t(Integer ingame_t) {
        this.ingame_t = ingame_t;
    }
}

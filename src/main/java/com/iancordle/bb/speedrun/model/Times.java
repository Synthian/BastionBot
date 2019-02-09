package com.iancordle.bb.speedrun.model;

public class Times {
    private String primary;
    private Integer primary_t;
    private String realtime;
    private Integer realtime_t;
    private String realtime_noloads;
    private Integer realtime_noloads_t;
    private String ingame;
    private Integer ingame_t;

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Integer getPrimary_t() {
        return primary_t;
    }

    public void setPrimary_t(Integer primary_t) {
        this.primary_t = primary_t;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public Integer getRealtime_t() {
        return realtime_t;
    }

    public void setRealtime_t(Integer realtime_t) {
        this.realtime_t = realtime_t;
    }

    public String getRealtime_noloads() {
        return realtime_noloads;
    }

    public void setRealtime_noloads(String realtime_noloads) {
        this.realtime_noloads = realtime_noloads;
    }

    public Integer getRealtime_noloads_t() {
        return realtime_noloads_t;
    }

    public void setRealtime_noloads_t(Integer realtime_noloads_t) {
        this.realtime_noloads_t = realtime_noloads_t;
    }

    public String getIngame() {
        return ingame;
    }

    public void setIngame(String ingame) {
        this.ingame = ingame;
    }

    public Integer getIngame_t() {
        return ingame_t;
    }

    public void setIngame_t(Integer ingame_t) {
        this.ingame_t = ingame_t;
    }
}

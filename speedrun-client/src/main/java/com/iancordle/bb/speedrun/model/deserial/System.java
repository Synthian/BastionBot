package com.iancordle.bb.speedrun.model.deserial;

public class System {

    private String platform;
    private Boolean emulated;
    private String region;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getEmulated() {
        return emulated;
    }

    public void setEmulated(Boolean emulated) {
        this.emulated = emulated;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

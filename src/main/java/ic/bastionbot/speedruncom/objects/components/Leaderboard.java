
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leaderboard {

    @SerializedName("weblink")
    @Expose
    private String weblink;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("video-only")
    @Expose
    private Boolean videoOnly;
    @SerializedName("timing")
    @Expose
    private String timing;
    @SerializedName("values")
    @Expose
    private Values values;
    @SerializedName("runs")
    @Expose
    private List<PlacedRun> runs = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getVideoOnly() {
        return videoOnly;
    }

    public void setVideoOnly(Boolean videoOnly) {
        this.videoOnly = videoOnly;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }

    public List<PlacedRun> getRuns() {
        return runs;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}

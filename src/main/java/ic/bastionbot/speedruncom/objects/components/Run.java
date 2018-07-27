
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.speedruncom.objects.CategoryResponse;
import ic.bastionbot.speedruncom.objects.PlayerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("weblink")
    @Expose
    private String weblink;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("category")
    @Expose
    private CategoryResponse categoryResponse;
    @SerializedName("videos")
    @Expose
    private Videos videos;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("players")
    @Expose
    private PlayerResponse playerResponse = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("submitted")
    @Expose
    private String submitted;
    @SerializedName("times")
    @Expose
    private Times times;
    @SerializedName("system")
    @Expose
    private System system;
    @SerializedName("splits")
    @Expose
    private Splits splits;
    @SerializedName("values")
    @Expose
    private Map<String,String> valueMap;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public String getId() {
        return id;
    }

    public String getWeblink() {
        return weblink;
    }

    public String getGame() {
        return game;
    }

    public String getLevel() {
        return level;
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public Videos getVideos() {
        return videos;
    }

    public String getComment() {
        return comment;
    }

    public Status getStatus() {
        return status;
    }

    public PlayerResponse getPlayerResponse() {
        return playerResponse;
    }

    public String getDate() {
        return date;
    }

    public String getSubmitted() {
        return submitted;
    }

    public Times getTimes() {
        return times;
    }

    public System getSystem() {
        return system;
    }

    public Splits getSplits() {
        return splits;
    }

    public String getVariable() {
        if (!valueMap.isEmpty())
            return valueMap.keySet().iterator().next();
        else
            return null;
    }

    public String getMode() {
        if (!valueMap.isEmpty())
            return valueMap.values().iterator().next();
        else
            return null;
    }

    public Map<String, String> getVariableMap() {
        Map<String, String> map = new HashMap<>();
        String key = "var-" + getVariable();
        String value = getMode();
        map.put(key, value);
        return map;
    }

    public List<Link> getLinks() {
        return links;
    }

    //TODO: Have useful functions here that pull data out of the Run?

}

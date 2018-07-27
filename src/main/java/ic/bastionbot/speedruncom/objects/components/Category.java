package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weblink")
    @Expose
    private String weblink;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("rules")
    @Expose
    private String rules;
    @SerializedName("players")
    @Expose
    private JsonObject players;
    @SerializedName("miscellaneous")
    @Expose
    private Boolean miscellaneous;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Boolean getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Boolean miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}

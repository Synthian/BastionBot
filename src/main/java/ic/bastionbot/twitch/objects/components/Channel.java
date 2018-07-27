
package ic.bastionbot.twitch.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("mature")
    @Expose
    private Boolean mature;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("broadcaster_language")
    @Expose
    private String broadcasterLanguage;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("partner")
    @Expose
    private Boolean partner;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("video_banner")
    @Expose
    private String videoBanner;
    @SerializedName("profile_banner")
    @Expose
    private String profileBanner;
    @SerializedName("profile_banner_background_color")
    @Expose
    private String profileBannerBackgroundColor;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("broadcaster_type")
    @Expose
    private String broadcasterType;
    @SerializedName("description")
    @Expose
    private String description;

    public Boolean getMature() {
        return mature;
    }

    public String getStatus() {
        return status;
    }

    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getGame() {
        return game;
    }

    public String getLanguage() {
        return language;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getPartner() {
        return partner;
    }

    public String getLogo() {
        return logo;
    }

    public String getVideoBanner() {
        return videoBanner;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public String getProfileBannerBackgroundColor() {
        return profileBannerBackgroundColor;
    }

    public String getUrl() {
        return url;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getFollowers() {
        return followers;
    }

    public String getBroadcasterType() {
        return broadcasterType;
    }

    public String getDescription() {
        return description;
    }
}

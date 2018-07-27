
package ic.bastionbot.twitch.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stream {

    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("game")
    @Expose
    private String game;
    @SerializedName("broadcast_platform")
    @Expose
    private String broadcastPlatform;
    @SerializedName("community_id")
    @Expose
    private String communityId;
    @SerializedName("community_ids")
    @Expose
    private List<String> communityIds = null;
    @SerializedName("viewers")
    @Expose
    private Integer viewers;
    @SerializedName("video_height")
    @Expose
    private Integer videoHeight;
    @SerializedName("average_fps")
    @Expose
    private Float averageFps;
    @SerializedName("delay")
    @Expose
    private Integer delay;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_playlist")
    @Expose
    private Boolean isPlaylist;
    @SerializedName("stream_type")
    @Expose
    private String streamType;
    @SerializedName("preview")
    @Expose
    private Preview preview;
    @SerializedName("channel")
    @Expose
    private Channel channel;

    public long getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getBroadcastPlatform() {
        return broadcastPlatform;
    }

    public String getCommunityId() {
        return communityId;
    }

    public List<String> getCommunityIds() {
        return communityIds;
    }

    public Integer getViewers() {
        return viewers;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public Float getAverageFps() {
        return averageFps;
    }

    public Integer getDelay() {
        return delay;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getPlaylist() {
        return isPlaylist;
    }

    public String getStreamType() {
        return streamType;
    }

    public Preview getPreview() {
        return preview;
    }

    public Channel getChannel() {
        return channel;
    }
}

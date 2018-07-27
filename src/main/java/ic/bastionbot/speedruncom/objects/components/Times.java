
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Times {

    @SerializedName("primary")
    @Expose
    private String primary;
    @SerializedName("primary_t")
    @Expose
    private Float primaryT;
    @SerializedName("realtime")
    @Expose
    private String realtime;
    @SerializedName("realtime_t")
    @Expose
    private Float realtimeT;
    @SerializedName("realtime_noloads")
    @Expose
    private String realtimeNoloads;
    @SerializedName("realtime_noloads_t")
    @Expose
    private Float realtimeNoloadsT;
    @SerializedName("ingame")
    @Expose
    private String ingame;
    @SerializedName("ingame_t")
    @Expose
    private Float ingameT;

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Float getPrimaryT() {
        return primaryT;
    }

    public void setPrimaryT(Float primaryT) {
        this.primaryT = primaryT;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public Float getRealtimeT() {
        return realtimeT;
    }

    public void setRealtimeT(Float realtimeT) {
        this.realtimeT = realtimeT;
    }

    public String getRealtimeNoloads() {
        return realtimeNoloads;
    }

    public void setRealtimeNoloads(String realtimeNoloads) {
        this.realtimeNoloads = realtimeNoloads;
    }

    public Float getRealtimeNoloadsT() {
        return realtimeNoloadsT;
    }

    public void setRealtimeNoloadsT(Float realtimeNoloadsT) {
        this.realtimeNoloadsT = realtimeNoloadsT;
    }

    public String getIngame() {
        return ingame;
    }

    public void setIngame(String ingame) {
        this.ingame = ingame;
    }

    public Float getIngameT() {
        return ingameT;
    }

    public void setIngameT(Float ingameT) {
        this.ingameT = ingameT;
    }

}

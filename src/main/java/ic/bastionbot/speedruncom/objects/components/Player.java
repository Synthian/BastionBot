
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("names")
    @Expose
    private Names names;
    @SerializedName("weblink")
    @Expose
    private String weblink;
    @SerializedName("name-style")
    @Expose
    private NameStyle nameStyle;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("signup")
    @Expose
    private String signup;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getId() {
        return id;
    }

    public Names getNames() {
        return names;
    }

    public String getWeblink() {
        return weblink;
    }

    public NameStyle getNameStyle() {
        return nameStyle;
    }

    public String getRole() {
        return role;
    }

    public String getSignup() {
        return signup;
    }

    public Location getLocation() {
        return location;
    }

}

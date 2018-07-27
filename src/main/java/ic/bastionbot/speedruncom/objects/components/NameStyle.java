
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameStyle {

    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("color")
    @Expose
    private Color color;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

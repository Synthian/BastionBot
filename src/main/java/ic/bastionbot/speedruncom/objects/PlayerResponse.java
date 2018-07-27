
package ic.bastionbot.speedruncom.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.speedruncom.objects.components.Player;

import java.util.List;


public class PlayerResponse {
    @SerializedName("data")
    @Expose
    private List<Player> data = null;

    public List<Player> getPlayers() {
        return data;
    }

    //TODO: Have functions here that pull useful data out of the response?
}


package ic.bastionbot.speedruncom.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.speedruncom.objects.components.Leaderboard;


public class LeaderboardResponse {
    @SerializedName("data")
    @Expose
    private Leaderboard data = null;

    public Leaderboard getLeaderboard() {
        return data;
    }

    //TODO: Have functions here that pull useful data out of the response?
}

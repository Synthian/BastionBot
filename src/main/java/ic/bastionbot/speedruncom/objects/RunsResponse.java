
package ic.bastionbot.speedruncom.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.speedruncom.objects.components.Pagination;
import ic.bastionbot.speedruncom.objects.components.Run;

import java.util.List;


public class RunsResponse {
    @SerializedName("data")
    @Expose
    private List<Run> data = null;

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Run> getRuns() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    //TODO: Have functions here that pull useful data out of the response?
}

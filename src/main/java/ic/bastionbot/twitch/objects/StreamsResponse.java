
package ic.bastionbot.twitch.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.twitch.objects.components.Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamsResponse {

    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("streams")
    @Expose
    private List<Stream> streams = null;

    public List<Stream> getStreams() {
        if (streams != null)
            return streams;
        else
            return new ArrayList<>();
    }

    public Integer getTotal() {
        return total;
    }
}

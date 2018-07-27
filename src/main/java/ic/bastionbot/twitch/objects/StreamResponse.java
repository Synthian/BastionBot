
package ic.bastionbot.twitch.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.twitch.objects.components.Stream;

public class StreamResponse {

    @SerializedName("stream")
    @Expose
    private Stream stream = null;

    public Stream getStream() {
        return stream;
    }
}

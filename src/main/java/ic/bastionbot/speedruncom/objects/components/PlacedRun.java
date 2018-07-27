
package ic.bastionbot.speedruncom.objects.components;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacedRun {

    @SerializedName("place")
    @Expose
    private Integer place;
    @SerializedName("run")
    @Expose
    private EmbedlessRun run;

    public EmbedlessRun getRun() {
        return run;
    }

    public Integer getPlace() {
        return place;
    }


    //TODO: Have useful functions here that pull data out of the Run?



}

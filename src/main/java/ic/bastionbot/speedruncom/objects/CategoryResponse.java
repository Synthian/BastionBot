
package ic.bastionbot.speedruncom.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ic.bastionbot.speedruncom.objects.components.Category;


public class CategoryResponse {
    @SerializedName("data")
    @Expose
    private Category data = null;

    public Category getCategory() {
        return data;
    }

    //TODO: Have functions here that pull useful data out of the response?
}

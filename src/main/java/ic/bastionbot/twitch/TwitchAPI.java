package ic.bastionbot.twitch;

import ic.bastionbot.Constants;
import ic.bastionbot.twitch.objects.StreamResponse;
import ic.bastionbot.twitch.objects.StreamsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitchAPI {

    private Retrofit retrofit;

    TwitchAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TWITCH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Fetches all streams for a specific game
    void fetchLiveStreams(String game, String stream_type, Callback<StreamsResponse> callback) {
        TwitchREST restInterface = retrofit.create(TwitchREST.class);
        Call<StreamsResponse> call = restInterface.getLiveStreams(game, stream_type);
        call.enqueue(callback);
    }

    //Fetches the information for a single stream
    void fetchChannelStream(long id, Callback<StreamResponse> callback) {
        TwitchREST restInterface = retrofit.create(TwitchREST.class);
        Call<StreamResponse> call = restInterface.getChannelStream(id);
        call.enqueue(callback);
    }
}

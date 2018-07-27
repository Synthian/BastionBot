package ic.bastionbot.twitch;

import ic.bastionbot.twitch.objects.StreamResponse;
import ic.bastionbot.twitch.objects.StreamsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface TwitchREST {

    @Headers({
            "Accept: application/vnd.twitchtv.v5+json",
            "Client-ID: wqgduum3fycq1kk1sti5vmv9nqi58p"
    })
    @GET("streams/")
    Call<StreamsResponse> getLiveStreams(@Query("game") String game, @Query("stream_type") String stream_type);

    @Headers({
            "Accept: application/vnd.twitchtv.v5+json",
            "Client-ID: wqgduum3fycq1kk1sti5vmv9nqi58p"
    })
    @GET("streams/{user_id}")
    Call<StreamResponse> getChannelStream(@Path("user_id") long id);
}


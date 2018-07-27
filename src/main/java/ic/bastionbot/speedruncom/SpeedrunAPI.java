package ic.bastionbot.speedruncom;

import ic.bastionbot.Constants;
import ic.bastionbot.speedruncom.objects.LeaderboardResponse;
import ic.bastionbot.speedruncom.objects.RunsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;

class SpeedrunAPI {

    private Retrofit retrofit;

    SpeedrunAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SPEEDRUN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    void fetchRecentRunsFromGame(String game, String orderby, String direction, String status, final Callback<RunsResponse> callback) {
        SpeedrunREST restInterface = retrofit.create(SpeedrunREST.class);
        Call<RunsResponse> call = restInterface.getRecentRunsFromGame(game, orderby, direction, status, Constants.EMBED_PLAYER_CATEGORY);
        call.enqueue(callback);
    }

    void fetchLeaderboard(String game, String category, final Callback<LeaderboardResponse> callback) {
        SpeedrunREST restInterface = retrofit.create(SpeedrunREST.class);
        Call<LeaderboardResponse> call = restInterface.getLeaderboard(game, category, Constants.MAX_PLACE_RESULTS);
        call.enqueue(callback);
    }

    void fetchLeaderboardWithVariable(String game, String category, Map<String, String> variableMap, final Callback<LeaderboardResponse> callback) {
        SpeedrunREST restInterface = retrofit.create(SpeedrunREST.class);
        Call<LeaderboardResponse> call = restInterface.getLeaderboardWithValue(game, category, Constants.MAX_PLACE_RESULTS, variableMap);
        call.enqueue(callback);
    }

}

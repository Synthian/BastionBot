package ic.bastionbot.speedruncom;

import ic.bastionbot.speedruncom.objects.LeaderboardResponse;
import ic.bastionbot.speedruncom.objects.RunsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

interface SpeedrunREST {

    @GET("/api/v1/runs")
    Call<RunsResponse> getRecentRunsFromGame(@Query("game") String game, @Query("orderby") String orderby, @Query("direction") String direction, @Query("status") String status, @Query("embed") String embed);

    @GET("/api/v1/leaderboards/{game}/category/{category}")
    Call<LeaderboardResponse> getLeaderboard(@Path("game") String game, @Path("category") String category, @Query("max") Integer max_entries);

    @GET("/api/v1/leaderboards/{game}/category/{category}")
    Call<LeaderboardResponse> getLeaderboardWithValue(@Path("game") String game, @Path("category") String category, @Query("max") Integer max_entries, @QueryMap Map<String, String> variableMap);
}

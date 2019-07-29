package com.iancordle.bb.speedrun;

import com.iancordle.bb.speedrun.requests.LeaderboardParams;
import com.iancordle.bb.speedrun.requests.RunsParams;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "speedrun-api", url = "${speedrun.api-url}")
public interface SpeedrunClient {
    @GetMapping("/leaderboards/{game}/category/{category}")
    String getLeaderboard(@PathVariable("game") String game,
                          @PathVariable("category") String category,
                          @RequestParam Map<String, String> variables,
                          @SpringQueryMap LeaderboardParams leaderboardParams);

    @GetMapping("/leaderboards/{game}/level/{level}/{category}")
    String getLevelLeaderboard(@PathVariable("game") String game,
                               @PathVariable("level") String level,
                               @PathVariable("category") String category,
                               @RequestParam Map<String, String> variables,
                               @SpringQueryMap LeaderboardParams leaderboardParams);

    @GetMapping("/runs")
    String getRuns(@SpringQueryMap RunsParams runsParams);
}

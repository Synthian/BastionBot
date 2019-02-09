package com.iancordle.bb.speedrun;

import com.iancordle.bb.speedrun.model.Run;
import com.iancordle.bb.speedrun.model.Wrapper;
import com.iancordle.bb.speedrun.requests.RunsQueryParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "speedrun-api", url = "${speedrun.url}")
public interface SpeedrunClient {

    @GetMapping("/runs")
    Wrapper<List<Run>> getRuns(@SpringQueryMap RunsQueryParams runsQueryParams);

}

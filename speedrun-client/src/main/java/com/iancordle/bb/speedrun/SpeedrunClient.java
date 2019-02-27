package com.iancordle.bb.speedrun;

import com.iancordle.bb.speedrun.requests.RunsParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "speedrun-api", url = "${speedrun.url}")
public interface SpeedrunClient {

    @GetMapping("categories/{id}")
    String getCategory(@PathVariable("id") String id, @RequestParam(value = "embed", required = false) List<String> embed);

    @GetMapping("/runs")
    String getRuns(@SpringQueryMap RunsParams runsParams);

}

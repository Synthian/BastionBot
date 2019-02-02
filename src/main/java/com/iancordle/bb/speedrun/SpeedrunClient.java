package com.iancordle.bb.speedrun;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${speedrun.url}")
public interface SpeedrunClient {

    

}

package com.scv.vadmin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "service-vinterface", fallback = VInterfaceTestHystric.class)
public interface VInterfaceTest {
	@RequestMapping(value = "/feignRespone"/*,method = RequestMethod.POST*/)
    String feignRespone(@RequestParam(value = "name") String name);
}

package com.scv.vinterface.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-vadmin", fallback = VAdminTestHystric.class)
public interface VAdminTest {
	@RequestMapping(value = "/feignRespone"/*,method = RequestMethod.POST*/)
    String feignRespone(@RequestParam(value = "name") String name);
}
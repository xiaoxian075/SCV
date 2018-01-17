package com.scv.vinterface.feign;

import org.springframework.stereotype.Component;

@Component
public class VAdminTestHystric implements VAdminTest {

	@Override
	public String feignRespone(String name) {
		return "vadmin feign call error";
	}


}

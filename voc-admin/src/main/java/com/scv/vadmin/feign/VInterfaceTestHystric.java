package com.scv.vadmin.feign;

import org.springframework.stereotype.Component;

@Component
public class VInterfaceTestHystric implements VInterfaceTest {

	@Override
	public String feignRespone(String name) {
		return "vinterface feign call error";
	}

}

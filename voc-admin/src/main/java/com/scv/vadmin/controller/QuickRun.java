package com.scv.vadmin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xx.kit.util.RedisUtil;

@RestController
@RequestMapping(value = "/v1")
public class QuickRun {
//	@RequestMapping(value = "/first", method = RequestMethod.GET)
//	public Map<String, Object> firstResp(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		request.getSession().setAttribute("request Url", request.getRequestURL());
//		map.put("request Url", request.getRequestURL());
//		return map;
//	}
//	
//	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
//	public Object sessions(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("sessionId", request.getSession().getId());
//		map.put("message", request.getSession().getAttribute("map"));
//		return map;
//	}
	
	@RequestMapping(value = "/first", method = RequestMethod.POST)
	public String firstResp(String sessionId, String name) {
		if (RedisUtil.set(sessionId, name, 15)) {
			return "SUCC";
		} else {
			return "FAIL";
		}
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public String sessions(String sessionId) {
		String name = RedisUtil.get(sessionId, String.class);
		return name;
	}
}

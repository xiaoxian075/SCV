package com.scv.vadmin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scv.vadmin.service.LogHelper;

@RestController
@RequestMapping("log")
public class LogController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("writelog")
	public Object writeLog() {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		new LogHelper().helpMethod();
		return "OK";
	}
}

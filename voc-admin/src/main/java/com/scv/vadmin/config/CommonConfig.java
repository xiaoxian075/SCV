package com.scv.vadmin.config;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.scv.vadmin.globle.bo.EnvValue;
import com.xx.kit.util.RedisUtil;

@Component
@Order(value=1)
public class CommonConfig implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private RedisTemplate<String, String> template;
	
	/**
	 * 环境变量
	 */
    @Value("${topic.admin}")
    private String topicAdmin;
    @Value("${topic.interface}")
    private String topicInterface;

	@Override
	public void run(String... arg0) throws Exception {
		
		if (StringUtils.isBlank(topicAdmin) || StringUtils.isBlank(topicInterface)) {
			logger.error("init env value error");
			return;
		}
		
		EnvValue.getInstance().setValue(topicAdmin, topicInterface);
		
		// 初始化Redis
		if (template == null) {
			logger.error("init redis error");
			return;
		}
		RedisUtil.init(template);
	}

}

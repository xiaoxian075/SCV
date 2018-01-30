package com.scv.vadmin.config;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.scv.vadmin.globle.RabbitmqTest;
import com.scv.vadmin.globle.bo.EnvValue;
import com.scv.vadmin.util.RabbitmqUtil;
import com.xx.kit.util.RedisUtil;

@Component
@Order(value=1)
public class CommonConfig implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private RedisTemplate<String, String> template;
	//private StringRedisTemplate redis;
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	/**
	 * 环境变量
	 */
    @Value("${topic.admin}")
    private String topicAdmin;
    @Value("${topic.interface}")
    private String topicInterface;
    @Value("${spring.rabbitmq.queue}")
    private String queue;

	@Override
	public void run(String... arg0) throws Exception {
		
		if (	StringUtils.isBlank(topicAdmin) || 
				StringUtils.isBlank(topicInterface)) {
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
		
		// 初始化rabbitmq
		if (rabbitTemplate == null) {
			logger.error("init rabbitmq error");
			return;
		}
		RabbitmqUtil.init(rabbitTemplate);
		
		if (StringUtils.isBlank(queue)) {
			logger.error("init rabbitmq queue error");
			return;
		}
		RabbitmqTest.getInstance().setQueue(queue);
	}

}

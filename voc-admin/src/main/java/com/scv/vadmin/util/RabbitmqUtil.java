package com.scv.vadmin.util;

import org.springframework.amqp.core.AmqpTemplate;

public class RabbitmqUtil {
	
	private static AmqpTemplate rabbitTemplate;
	
	public static void init(AmqpTemplate _rabbitTemplate) {
		rabbitTemplate = _rabbitTemplate;
	}
	
	public static boolean send(String queue, String msg) {
		try {
			rabbitTemplate.convertAndSend(queue, msg);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
}

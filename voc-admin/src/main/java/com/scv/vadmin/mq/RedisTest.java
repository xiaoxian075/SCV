package com.scv.vadmin.mq;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scv.vadmin.globle.bo.EnvValue;
import com.xx.kit.mq.RedisInter;
import com.xx.kit.util.RedisBo;
import com.xx.kit.util.RedisUtil;

public class RedisTest implements RedisInter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void excute(String topic, Integer msgId, String data, Long timestamp) {
		System.out.println("topic:" + topic);
		System.out.println("msgId:" + msgId);
		System.out.println("data:" + data);
		System.out.println("timestamp:" + timestamp);
		
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("code", 0);
    	params.put("info", "call succ");
    	if (!RedisUtil.sendMessage(RedisBo.createRedis(EnvValue.getInstance().getTopicAdmin(), 1001, params))) {
    		logger.error("push fail. {}", params);
    	}
	}

}

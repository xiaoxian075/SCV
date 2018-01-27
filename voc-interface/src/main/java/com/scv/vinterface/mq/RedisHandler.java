package com.scv.vinterface.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xx.kit.mq.BaseRedisHandler;
import com.xx.kit.mq.RedisInter;

/**
 * @ClassName 
 * @Description 
 * @author chenjx
 * @date 2017年10月16日 上午9:57:51
 */
public class RedisHandler extends BaseRedisHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected RedisInter create(int msgId) {
		RedisInter redisInter = null; 
		switch (msgId) {
		case 1001:
			redisInter = new RedisTest();
			break;
		default:
			{
				logger.info("mq msgId not find. msgId={}", msgId);
				redisInter = null;
			}
			break;
		}
		
		return redisInter;
	}
}


package com.scv.vadmin.globle;

import com.scv.vadmin.util.RabbitmqUtil;

/**
 * rabbit mq Test频道
 * @author Administrator
 *
 */
public class RabbitmqTest {
	// 私有构造方法，防止被实例化
	private RabbitmqTest() {
	}

	// 此处使用一个内部类来维护单例
	private static class RabbitmqTestFactory {
		private static RabbitmqTest instance = new RabbitmqTest();
	}

	// 获取实例
	public static RabbitmqTest getInstance() {
		return RabbitmqTestFactory.instance;
	}
		
//    @Value("${spring.rabbitmq.queue}")
    private String queue;
    
    public boolean sendMsg(String msg) {
    	if (msg == null || msg.length() == 0) {
    		return false;
    	}
    	return RabbitmqUtil.send(queue, msg);
    }

	public void setQueue(String _queue) {
		queue = _queue;
	}
}

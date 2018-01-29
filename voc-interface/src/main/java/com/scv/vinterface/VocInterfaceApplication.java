package com.scv.vinterface;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.scv.vinterface.mq.RedisHandler;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class VocInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocInterfaceApplication.class, args);
	}
	
	//配置redis订阅功能
    @Value("${topic.admin}")
    private String topicAdmin;
//    @Value("${topic.interface}")
//    private String topicInterface;
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		//admin频道
		container.addMessageListener(listenerAdapter, new PatternTopic(topicAdmin));
		//inter频道
		//container.addMessageListener(listenerAdapter, new PatternTopic(topicInterface));
		return container;
	}
	@Bean
	MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter(new RedisHandler(), RedisHandler.FUNCTION_NAME);
	}
}

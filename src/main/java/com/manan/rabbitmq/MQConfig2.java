package com.manan.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig2 {
	
public static final String USER_REG_QUEUE="user_reg_queue";
	
	public static final String USER_ROUTING_KEY="user_routing_key";
	
	public static final String ADMIN_EXCHANGE="admin_exchange";
	
	@Bean
	public Queue userRegQueue()
	{
		return new Queue(USER_REG_QUEUE);
	}
	
	@Bean
	public TopicExchange topicExchange()
	{
		return new TopicExchange(ADMIN_EXCHANGE);
	}
	
	
	@Bean
	public Binding userRegQueuebinding()
	{
		return BindingBuilder.bind(userRegQueue()).to(topicExchange()).with(USER_ROUTING_KEY);
	}

}

package com.manan.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MQConfig {
	
	public static final String QUEUE="productDetailQueue";
	public static final String EXCHANGE="DetailExchange";
	public static final String ROUTING_KEY="productRoutingKey";
			
	@Bean
	@Primary
	public Queue messageQueue()
	{
		return new Queue(QUEUE);
	}
	
	@Bean
	@Primary
	public TopicExchange messageExchange()
	{
		return new TopicExchange(EXCHANGE);
	}
	
	
	@Bean
	@Primary
	public Binding binding(Queue queue, TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	@Primary
	public MessageConverter messageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	@Primary
	public AmqpTemplate template(ConnectionFactory connectionFactory)
	{
		RabbitTemplate template=new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}

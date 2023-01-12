package com.manan.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/message/publish")
	public String publishMessage(@RequestBody ProductDTO product)
	{
				
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, product);
		return "Message Published to queue!";
		
	}
	
	@PostMapping("/send/user")
	public String publishUser(@RequestBody User user)
	{
		template.convertAndSend(MQConfig2.ADMIN_EXCHANGE, MQConfig2.USER_ROUTING_KEY, user);
		return "User details published to queue!";
	}

}

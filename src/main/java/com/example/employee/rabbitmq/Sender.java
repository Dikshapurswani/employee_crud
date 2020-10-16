package com.example.employee.rabbitmq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

	public void sendMessage(RabbitTemplate template, String exchange, Object data,
			String key) {

		template.convertAndSend(exchange, key, data);
	}
}

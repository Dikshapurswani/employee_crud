package com.example.employee.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.config.RabbitmqConfig;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.InternalServerError;
import com.example.employee.rabbitmq.Sender;


@RestController
@RequestMapping("/employee")
public class RabbitMQController {
	
	/* 
	 * Using rabbitMq with update api
	 */
	@Autowired
	private Sender sender;
	@Autowired
	private RabbitmqConfig rabbitmqConfig;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//Update
	@PutMapping("/queue")
	public String  updateEmployee(@RequestBody EmployeeDto employee) throws InternalServerError{
		sender.sendMessage(rabbitTemplate, rabbitmqConfig.getExchange(),
				employee, rabbitmqConfig.getRoutingKey());

		return "message sent to queue";
	}
	

}

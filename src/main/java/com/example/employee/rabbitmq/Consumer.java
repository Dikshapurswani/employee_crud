package com.example.employee.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.InternalServerError;
import com.example.employee.service.EmployeeService;

@Component
public class Consumer {

	@Autowired
	private EmployeeService employeeService;

	@RabbitListener(queues = "${rabbitmq.queue}")
	public void recievedMessage(EmployeeDto employee) throws InternalServerError{
		String message=employeeService.save(employee);
		System.out.println(message);
	}
}

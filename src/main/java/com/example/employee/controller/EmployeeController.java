package com.example.employee.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.config.RabbitmqConfig;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.rabbitmq.Sender;
import com.example.employee.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	/* 
	 * Using rabbitMq with update api
	 */
	@Autowired
	private Sender sender;
	@Autowired
	private RabbitmqConfig rabbitmqConfig;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping("/employees")
	public List<EmployeeDto> findAll(){
		return employeeService.findAll();
	}
	
	
	@GetMapping("/employees/{id}")
	public EmployeeDto getEmployee(@PathVariable String id ) {
		   EmployeeDto employee=employeeService.findById(id);
		   
		   if( employee==null){
			   throw new RuntimeException("Employee not found"+id);
		   }
		   return employee;
	}
	
	@PostMapping("employees")
	public String addEmployee(@RequestBody EmployeeDto employee ) {
		String message=employeeService.save(employee);
		return message;
		
	}
	
	//Update
		@PutMapping("/employees")
		public String  updateEmployee(@RequestBody EmployeeDto employee) {
			sender.sendMessage(rabbitTemplate, rabbitmqConfig.getExchange(),
					employee, rabbitmqConfig.getRoutingKey());
//			String message=employeeService.save(employee);
			return "message sent to queue";
		}
		
		//Delete
		@DeleteMapping("/employees/{id}")
		public String deleteEmployee(@PathVariable String id) {
			EmployeeDto employee=employeeService.findById(id);
			if (employee==null) {
				throw new RuntimeException("employee not found"+id);
			}
			
			else {
				return employeeService.delete(id);
//				return "deleted employee"+id;
			}
		}
		
		
}

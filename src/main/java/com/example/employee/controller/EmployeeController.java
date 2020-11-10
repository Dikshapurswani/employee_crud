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
import com.example.employee.exception.BadRequest;
import com.example.employee.exception.InternalServerError;
import com.example.employee.rabbitmq.Sender;
import com.example.employee.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping("/getAll")
	public List<EmployeeDto> findAll() throws InternalServerError{
		return employeeService.findAll();
	}
	
	
	@GetMapping("/find/{id}")
	public EmployeeDto getEmployee(@PathVariable String id ) throws InternalServerError,BadRequest {
		   EmployeeDto employee=employeeService.findById(id);
		   
		   if( employee==null){
			   throw new RuntimeException("Employee not found"+id);
		   }
		   return employee;
	}
	
	@PostMapping("/add")
	public String addEmployee(@RequestBody EmployeeDto employee ) throws InternalServerError{
		String message=employeeService.save(employee);
		return message;
		
	}
	
	//Update
		@PutMapping("/update")
		public String  updateEmployee(@RequestBody EmployeeDto employee) throws InternalServerError{
			String message=employeeService.save(employee);
			return message;
		}
		
		//Delete
		@DeleteMapping("/delete/{id}")
		public String deleteEmployee(@PathVariable String id) throws BadRequest{
			String message=employeeService.delete(id);
			return message;
		}
		
		
}

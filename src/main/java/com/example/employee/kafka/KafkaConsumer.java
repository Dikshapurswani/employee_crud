package com.example.employee.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.InternalServerError;
import com.example.employee.service.EmployeeService;

@Service
public class KafkaConsumer {
	
	@Autowired
    private EmployeeService employeeService;

    @KafkaListener(topics = "Employee")
    public void consume(EmployeeDto employeeDto) throws InternalServerError {
        employeeService.save(employeeDto);
  
    }
}

package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.kafka.KafkaProducer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
	@Autowired
	public KafkaProducer kafkaProducer;
	
	@PutMapping("/publish")
    public String sendMessageToKafkaTopic(@RequestBody EmployeeDto employeeDto) {
        kafkaProducer.sendMessage(employeeDto);
        return "message sent to cluster";
      
    }
	

}

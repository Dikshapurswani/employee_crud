package com.example.employee.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeDto;

@Service
public class KafkaProducer {
	
	private static final String TOPIC = "Employee";

    @Autowired
    private KafkaTemplate<String, EmployeeDto> kafkaTemplate;

    public void sendMessage(EmployeeDto employeeDto) {
      
        kafkaTemplate.send(TOPIC, employeeDto);
      
    }

}

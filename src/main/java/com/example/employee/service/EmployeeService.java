package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.BadRequest;
import com.example.employee.exception.InternalServerError;

public interface EmployeeService {
	
	/* 
	 * gets all employees
	 */

	public List<EmployeeDto> findAll() throws InternalServerError;
	
	
	/*
	 * gets employee with given id
	 */
	
	public EmployeeDto findById(String id) throws InternalServerError,BadRequest;
	
	/* 
	 * adds employee or update if already present
	 */
	
	public String save(EmployeeDto employee) throws InternalServerError;
	
	/* 
	 * deletes employee
	 */
	
	public String delete(String id) throws BadRequest;
	
	
	
	
}

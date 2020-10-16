package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.EmployeeDto;

public interface EmployeeService {
	
	/* 
	 * gets all employees
	 */

	public List<EmployeeDto> findAll();
	
	
	/*
	 * gets employee with given id
	 */
	
	public EmployeeDto findById(String id);
	
	/* 
	 * adds employee or update if already present
	 */
	
	public String save(EmployeeDto employee);
	
	/* 
	 * deletes employee
	 */
	
	public String delete(String id);
	
	
	
	
}

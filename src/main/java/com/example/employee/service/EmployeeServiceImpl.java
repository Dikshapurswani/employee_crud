package com.example.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.exception.BadRequest;
import com.example.employee.exception.InternalServerError;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	

	ModelMapper modelMapper = new ModelMapper();
	@Override
	public List<EmployeeDto> findAll() throws InternalServerError{
		try {
			List<EmployeeEntity> employeeEntityList=employeeRepository.findAll();
			List<EmployeeDto> employeeDtoList=employeeEntityList
					.stream()
					.map(employee -> modelMapper.map(employee, EmployeeDto.class))
					 .collect(Collectors.toList());
					
			return employeeDtoList;
		}
		catch(Exception e) {
			throw new InternalServerError("Employees couldn't be fetched");
		}
		
	}

	@Override
	public EmployeeDto findById(String id) throws BadRequest,InternalServerError {
		Optional<EmployeeEntity> employee=employeeRepository.findById(id );
		EmployeeDto employeeDto=null;
		if (employee.isPresent()) {
			try {
				employeeDto=modelMapper.map(employee.get(), EmployeeDto.class);
			}
			catch(Exception e) {
				new InternalServerError("Employee couldn't be fetched");
			}			
		}
		else {
			throw new BadRequest("Could not finf employee with id :"+id);
		}
		return employeeDto;
		
		
	}

	@Override
	public String save(EmployeeDto employee) throws InternalServerError{
		String message=null;
		String id=employee.getId();
		String phoneNumber=employee.getPhoneNumber();
		String firstName=employee.getFirstName();
		String lastName=employee.getLastName();
		String email=employee.getEmailId();
		/* 
		 * if any of above is null run update
		 */
		if(phoneNumber==null || firstName==null || lastName==null || email==null) {
			
			try {
				EmployeeEntity emp1=modelMapper.map(this.findById(id), EmployeeEntity.class); 
				if(phoneNumber==null) {
					phoneNumber=emp1.getPhoneNumber();			
					}
				if(firstName==null) {
					firstName=emp1.getFirstName()	;		
					}
				if(lastName==null) {
					lastName=emp1.getLastName();			
					}
				if(email==null) {
					email=emp1.getEmailId();			
					}
				employee.setPhoneNumber(phoneNumber);
				employee.setFirstName(firstName);
				employee.setLastName(lastName);
				employee.setEmailId(email);
				
				EmployeeDto emp=modelMapper.map(employeeRepository.saveAndFlush
						(modelMapper.map(employee, EmployeeEntity.class)),EmployeeDto.class);
				message="Successfully updated employee with id: "+emp.getId();
			}
			catch(Exception e) {
				throw new InternalServerError("Employee couldn't be updated with id :"+id);
			}
		
			
		}
		/* 
		 * insert a new employee
		 */
		else {
			try {
				EmployeeDto emp=modelMapper.map(employeeRepository.saveAndFlush
						(modelMapper.map(employee, EmployeeEntity.class)),EmployeeDto.class);
				message="Successfully added employee with id: "+emp.getId();
			}
			catch(Exception e) {
				throw new InternalServerError("Employee couldn't be added with id :"+id);
			}
			
		}
		return message;
		   
		
	}

	@Override
	public String delete(String id) throws BadRequest {
	 
		try {
			employeeRepository.deleteById(id );
			return "Successfully deleted employee with id: "+id;
		}
		catch(Exception e) {
			throw new BadRequest("Employee with id "+id+" couldn't be found");
		}
	}

}

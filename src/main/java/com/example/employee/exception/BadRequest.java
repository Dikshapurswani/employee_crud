package com.example.employee.exception;

public class BadRequest extends Exception{
     
	public String message;
	
	public BadRequest(String message) {
		this.message=message;
	}
	
	@Override
   public String getMessage() {
	   return message;
   }
	
}

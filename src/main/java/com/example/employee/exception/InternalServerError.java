package com.example.employee.exception;

public class InternalServerError extends Exception{

    public String message;
	
	public InternalServerError(String message) {
		this.message=message;
	}
	
	@Override
   public String getMessage() {
	   return message;
   }
	
}

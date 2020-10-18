package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Global Exception Handler, to allow handling globally not only on single controller
 */

@ControllerAdvice
public class EmployeeExceptionHandler {

	/*
	 *handles exception from any controller api 
	 */
	
	@ExceptionHandler(value=BadRequest.class)
	public ResponseEntity<Object> exception(BadRequest e) {
		/*
		 * to manipulate the http header and body
		 */
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value=InternalServerError.class)
	public ResponseEntity<Object> exception(InternalServerError e) {
		return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}

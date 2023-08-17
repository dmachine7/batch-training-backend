package com.bankapp.app.exceptions;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Exception_m> resourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request){
		Exception_m message = new Exception_m(HttpStatus.NOT_FOUND.value()
				,new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<Exception_m>(message,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception_m> globalExceptionHandler(Exception ex,
			WebRequest request){
		Exception_m message = new Exception_m(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,new Date(),ex.getMessage(),request.getDescription(false));
		return  new ResponseEntity<Exception_m>(message,HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
}

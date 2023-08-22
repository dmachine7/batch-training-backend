package com.bankapp.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Exception_m> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request){
		Exception_m message = new Exception_m(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,new Date(),ex.getBindingResult().
						getFieldErrors().stream().map(err -> err.getDefaultMessage())
						.collect(java.util.stream.Collectors.joining(", "))
				,request.getDescription(false));
		
		return new ResponseEntity<Exception_m>(message,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception_m> globalExceptionHandler(Exception ex,
			WebRequest request){
		Exception_m message = new Exception_m(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,new Date(),ex.getMessage(),request.getDescription(false));
		return  new ResponseEntity<Exception_m>(message,HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
}

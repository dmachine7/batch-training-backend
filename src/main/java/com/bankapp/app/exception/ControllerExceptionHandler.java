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
	public ResponseEntity<ExceptionCustom> resourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request){
		ExceptionCustom message = new ExceptionCustom(HttpStatus.NOT_FOUND.value()
				,new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<ExceptionCustom>(message,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionCustom> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request){
		ExceptionCustom message = new ExceptionCustom(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,new Date(),ex.getBindingResult().
						getFieldErrors().stream().map(err -> err.getDefaultMessage())
						.collect(java.util.stream.Collectors.joining(", "))
				,request.getDescription(false));
		
		return new ResponseEntity<ExceptionCustom>(message,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionCustom> globalExceptionHandler(Exception ex,
			WebRequest request){
		ExceptionCustom message = new ExceptionCustom(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,new Date(),ex.getMessage(),request.getDescription(false));
		return  new ResponseEntity<ExceptionCustom>(message,HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
}

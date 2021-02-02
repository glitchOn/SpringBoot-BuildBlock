package com.ck.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "Method Arguments are not valid", ex.getMessage());
		
		return new  ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
			CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "Request method not supported", ex.getMessage());
			
			return new  ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);	

	}
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(
		UserNameNotFoundException ex, WebRequest req) {
		

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		
		return new  ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(
		ConstraintViolationException ex, WebRequest req) {
		

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		
		return new  ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);		
	}
	
	
}

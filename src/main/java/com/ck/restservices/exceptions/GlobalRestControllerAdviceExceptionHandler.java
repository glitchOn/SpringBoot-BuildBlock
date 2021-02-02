package com.ck.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final CustomErrorDetails handleUserNameNotFoundException(
		UserNameNotFoundException ex, WebRequest req) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		
		return customErrorDetails;
	}
}

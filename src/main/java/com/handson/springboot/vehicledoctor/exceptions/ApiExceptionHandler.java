package com.handson.springboot.vehicledoctor.exceptions;


import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception){
		
		ApiException apiException = new ApiException(exception.getMessage(), 
				HttpStatus.BAD_REQUEST, 
				ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}
}

package com.nt.global.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException exception) {

		return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException except) {

		return ResponseEntity.status(except.getStatus()).body(except.getMessage());
	}
	
	
}

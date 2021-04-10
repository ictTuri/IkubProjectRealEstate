package com.realestate.app.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.realestate.app.dto.ErrorFormat;
import com.realestate.app.exceptions.MyExcMessages;

@Component
@RestControllerAdvice
public class MyExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	        errors.put("error","Validation Error");
	    });
	    return errors;
	}
	 @ExceptionHandler(value = { MyExcMessages.class })
		    protected ResponseEntity<Object> handleCustomExceptions( RuntimeException ex, WebRequest request) {
		       ErrorFormat errorBody=new ErrorFormat();
		       errorBody.setMessage(ex.getMessage());
		       errorBody.setDesc(request.getDescription(true));
		       errorBody.setSuggestion("Contact Admin");
		       
		       
		        return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);
		      
		    }

}

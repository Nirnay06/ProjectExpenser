package com.expenser.exception;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.expenser.model.APIResponseDTO;

@ControllerAdvice
public class ExpenserExceptionHandler extends ResponseEntityExceptionHandler{
		
	@ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Access Denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Object> handleBusinessException(
      BusinessException ex, WebRequest request) {
        return new ResponseEntity<>(new APIResponseDTO(ex.getMessage(), false),
				HttpStatus.BAD_REQUEST);
    }
}	

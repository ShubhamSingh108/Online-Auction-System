package com.app.auction.exception_handler;

import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.auction.dto.ApiResponse;
import com.app.auction.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) 
		{
			System.out.println("in global handler : method arg invalid");
			Map<String,String> error=ex.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
				
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}

		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
			System.out.println("in run time exc handler");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse(e.getMessage()));
		}

	

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		
	
	}
	
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<?>
	 * handleException(Exception e) { System.out.println("in run time exc handler");
	 * e.printStackTrace(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
	 * ErrorResponse(e.getMessage())); }
	 */

}

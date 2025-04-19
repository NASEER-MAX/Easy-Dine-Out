package com.restaurent.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.restaurent.dto.ErrorResponse;
import com.restaurent.exception.DependencyException;
import com.restaurent.exception.DuplicateEntityFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ExceptionInterceptor {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		log.error("Got MethodArgumentNotValidException: ", e);

		FieldError fieldError = e.getBindingResult().getFieldError();

		String errorMessage = fieldError.getDefaultMessage();

		if (StringUtils.isBlank(errorMessage)) {
			errorMessage = "Invalid Value: " + fieldError.getRejectedValue() + " for Field: " + fieldError.getField();
		}

		ErrorResponse error = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		log.error("Got HttpMessageNotReadableException: ", e);

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

		log.error("Got DataIntegrityViolationException");

		ErrorResponse errorResponse = new ErrorResponse("Data integrity violation: " + e.getMessage(),
				HttpStatus.CONFLICT.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConcurrencyFailureException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ResponseEntity<ErrorResponse> handleConcurrencyFailureException(ConcurrencyFailureException e) {

		log.error("Got ConcurrencyFailureException");

		ErrorResponse errorResponse = new ErrorResponse("Concurrency Exception: " + e.getMessage(),
				HttpStatus.CONFLICT.value());

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateEntityFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleDuplicateEntityFoundException(DuplicateEntityFoundException e) {

		log.error("Got DuplicateEntityFoundException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DependencyException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleDependencyException(DependencyException e) {

		log.error("Got DependencyException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityExistsException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException e) {

		log.error("Got EntityExistsException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {

		log.error("Got EntityNotFoundException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {

		log.error("Got ExpiredJwtException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {

		log.error("Got AccessDeniedException");

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {

		log.error("Got Exception");
		e.printStackTrace();

		ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

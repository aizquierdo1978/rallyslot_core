package com.aizquierdo.rallyslot.core.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aizquierdo.rallyslot.core.dto.common.ApiError;
import com.aizquierdo.rallyslot.core.exception.EntityNotFoundException;
import com.aizquierdo.rallyslot.core.exception.InvalidEntityException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle entity not found exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<ApiError> handleEntityNotFoundException(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(ex.getMessage(), ((EntityNotFoundException) ex).getErrors());
		return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
	}


	/**
	 * Handle invalid entity exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ InvalidEntityException.class })
	public ResponseEntity<ApiError> handleInvalidEntityException(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(ex.getMessage(), ((InvalidEntityException) ex).getErrors());
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle invalid entity exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ApiError> handleOtherExceptions(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(ex.getMessage());
		return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

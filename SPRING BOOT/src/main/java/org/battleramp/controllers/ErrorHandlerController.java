package org.battleramp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.battleramp.response.GenericExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public GenericExceptionResponse handleException(final Exception exception, final HttpServletRequest request,
			final HttpServletResponse responset) {

		GenericExceptionResponse error = new GenericExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		error.setTimestamp(System.currentTimeMillis());
		return error;
	}

}

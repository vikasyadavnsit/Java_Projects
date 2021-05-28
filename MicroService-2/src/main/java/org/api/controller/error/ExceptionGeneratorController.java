package org.api.controller.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionGeneratorController extends Throwable {

	private static final long serialVersionUID = 792545703381903428L;

	@RequestMapping("/globalExceptionHandler")
	public void globalExceptionHandler() throws Throwable {
		throw new Exception("Generic Exception Occured");
	}

	@RequestMapping("/localExceptionHandler")
	public void localExceptionHandler() throws RuntimeException {
		throw new RuntimeException("Generic Exception Occured");
	}

	@ExceptionHandler(RuntimeException.class)
	public String handleError(final Exception exception) {
		return "Local Exception Handler Invoked " + exception.getMessage();
	}

}

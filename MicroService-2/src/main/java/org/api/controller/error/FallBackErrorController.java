package org.api.controller.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.api.wrapper.generic.ResourceNotFoundWrapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackErrorController implements ErrorController {

	@RequestMapping("/error")
	public ResourceNotFoundWrapper handleError() {
		ResourceNotFoundWrapper wrapper = new ResourceNotFoundWrapper();
		wrapper.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
		wrapper.setMessage(HttpStatus.NOT_FOUND.name());
		wrapper.setDescription(
				"page you are trying doesn't exist or has been moved to a new location, please contact administrator.");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		wrapper.setTime(formatter.format(LocalDateTime.now()));
		return wrapper;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}

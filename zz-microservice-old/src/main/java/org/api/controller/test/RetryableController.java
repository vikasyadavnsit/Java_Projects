package org.api.controller.test;

import org.api.services.RetryableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/retryable")
public class RetryableController {

	public static ThreadLocal<Integer> attempts = new ThreadLocal<>();

	@Autowired
	private RetryableServiceImpl retryableService;

	@RequestMapping("/run")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ResponseBody
	public String mockRetryableBehaviour() {
		attempts.set(0);
		try {
			retryableService.retryService();
		} catch (Exception e) {
			log.error("Exception Inside RetryableController : mockRetryableBehaviour() Method");
			return "Mocking Retryable Behaviour... Failed with exception";
		}
		return "Mocking Retryable Behaviour... Successful";
	}
}

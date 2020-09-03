package itgi.portal.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import itgi.portal.boot.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	private TestService testService;

	@Autowired
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@GetMapping()
	ResponseEntity<String> root() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("key1", "val1");
		headers.add("key2", "String-only-please");
		return ResponseEntity.ok().headers(headers).body("Authorized");
	}

	@GetMapping("/err/{statusCode:[12345]{1}[0-9]{2}}")
	void err(@PathVariable() int statusCode) {
		switch (statusCode) {
		case 300 -> throw new RuntimeException();
		}
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "BAD_REQUEST")
	void exceptionHandler() {

	}

}

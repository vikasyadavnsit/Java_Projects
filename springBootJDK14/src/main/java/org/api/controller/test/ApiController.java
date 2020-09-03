package org.api.controller.test;

import java.util.Optional;
import java.util.function.Consumer;

import org.api.services.TestService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class ApiController {

	private TestService1 testService;

	@Autowired
	public void setTestService(TestService1 testService) {
		this.testService = testService;
	}

	@GetMapping("")
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

	@RequestMapping(value = "/api{specialOnly:[^a-zA-Z0-9]{3,9}}", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = { "key=val" }, params = {
					"key2=val2" }, produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
	ResponseEntity<String> rootContext(@PathVariable() String specialOnly, @RequestParam("key2") Optional<String> s) {

		StringBuffer sb = new StringBuffer("Authorized");

		s.ifPresent(new Consumer<String>() {
			@Override
			public void accept(String t) {
				sb.append(" with : " + s.get());
			}
		});

		return new ResponseEntity<String>(sb.toString(), HttpStatus.FORBIDDEN);
	}
}

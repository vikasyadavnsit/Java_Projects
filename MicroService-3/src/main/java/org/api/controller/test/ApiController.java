package org.api.controller.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.api.services.TestService1;
import org.api.wrapper.generic.PersonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/api")
public class ApiController {

	private TestService1 testService;

	@Autowired
	public void setTestService(TestService1 testService) {
		this.testService = testService;
	}

	@GetMapping("/default")
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
		case 500 -> throw new InternalError();
		}
	}

//	{
//		"id":12,
//		"firstName":"Vikas",
//		"LASTname":"Yadav"
//	}
	@PostMapping(value = "/caseInsensitiveCustomMapping")
	public PersonWrapper caseInsensitiveCustomMapping(@RequestBody Map<String, Object> map) {
		log.info("Inside APIController : caseInsensitiveCustomMapping() Method : Data -> " + map.toString());
		// Mapping map object to PersonWrapper manually or by creating a PersonWrapper
		// class with all field as lowercase or uppercase
		Map<String, String> temp = new HashMap<>();
		map.keySet().forEach(x -> temp.put(x.toLowerCase(), map.get(x).toString()));
		PersonWrapper obj = new ObjectMapper().convertValue(temp, PersonWrapper.class);
		return obj;
	}

	@PostMapping(value = "/caseInsensitiveJackson")
	public PersonWrapper caseInsensitiveJackson(@RequestBody PersonWrapper wrapper) {
		log.info("Inside APIController : caseInsensitiveJackson() Method : Data -> " + wrapper.toString());
		// Mapping is implicilty done by jackson, after configuring jackson case
		// insesitive setting
		return wrapper;
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

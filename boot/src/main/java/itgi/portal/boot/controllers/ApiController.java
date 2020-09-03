package itgi.portal.boot.controllers;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

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

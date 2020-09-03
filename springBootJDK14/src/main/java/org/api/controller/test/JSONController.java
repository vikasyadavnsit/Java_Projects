package org.api.controller.test;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class JSONController {

	@RequestMapping("")
	public ResponseEntity<String> allHandlers() {

		JSONObject entity = new JSONObject();
		entity.put("status", HttpStatus.NOT_FOUND.value());
		entity.put("error", "URL Mapping Not Found");
		return new ResponseEntity<String>(entity.toString(), HttpStatus.NOT_FOUND);
	}

}

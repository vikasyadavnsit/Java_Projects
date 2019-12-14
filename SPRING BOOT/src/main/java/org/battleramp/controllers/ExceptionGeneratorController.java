package org.battleramp.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExceptionGeneratorController extends Throwable {

	private static final long serialVersionUID = 792545703381903428L;

	@RequestMapping("/globalExceptionHandler")
	public void globalExceptionHandler() throws Exception {
		throw new Exception("Generic Exception Occured");
	}

	@RequestMapping("/localExceptionHandler")
	public void localExceptionHandler() throws Exception {
		throw new Exception("Generic Exception Occured");
	}

	@RequestMapping("/jsonObjectSerializer")
	@ResponseBody
	public String welcomeJSONObject() {
		JSONObject entity = new JSONObject();
		entity.put("json", "JSON Response");
		entity.put("object", System.currentTimeMillis());
		return entity.toString();
	}

	@ExceptionHandler(Exception.class)
	public String handleError(final Exception exception) {
		return "Local Exception Handler Invoked " + exception.getMessage();
	}

}

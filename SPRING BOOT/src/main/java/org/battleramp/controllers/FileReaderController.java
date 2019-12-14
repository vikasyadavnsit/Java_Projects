package org.battleramp.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.battleramp.wrapper.TestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FileReaderController {

	@PostMapping(path = "readFile", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> readFile(@RequestBody List<TestWrapper> data) {
		System.out.println(data.toArray());
		return new ResponseEntity<String>("Data Recieved Size :" + data.size(), HttpStatus.OK);
	}

	@PostMapping(path = "readParam/{identifier}/{id}")
	public ResponseEntity<String> readParam(@PathVariable int id, @PathVariable("identifier") String param,
			@RequestParam String name) {
		System.out.println("Param is :" + name);
		System.out.println("Path Param is : " + param);
		System.out.println("Variable Param is : " + id);
		return new ResponseEntity<String>("Params Recieved", HttpStatus.OK);
	}

}
package org.api.controller.test;

import java.util.Date;
import java.util.List;

import org.api.services.DatatbaseService;
import org.api.wrapper.generic.TestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private DatatbaseService databaseService;

	@GetMapping(path = "writeFile", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<TestWrapper>> test(@RequestHeader("Accept") String acceptType) {
		System.out.println("Accept Type : " + acceptType);
		HttpHeaders headers = new HttpHeaders();
		headers.add("dateTimeStamp", new Date().toString());
		headers.add("userName", "vikas");
		headers.add("password", "yadav");
		return new ResponseEntity<List<TestWrapper>>(databaseService.getAllData(), headers, HttpStatus.OK);
	}

	@PostMapping(path = "readFile", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> readFile(@RequestBody List<TestWrapper> data) {
		System.out.println(data.toArray());
		return new ResponseEntity<String>("Data Recieved Size :" + data.size(), HttpStatus.OK);
	}

}
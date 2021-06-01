package org.api.domain.controller;

import org.api.domain.LibraryEvent;
import org.api.domain.producer.LibraryEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LibraryEventsController {

	@Autowired
	LibraryEventProducer libraryEventProducer;

	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent)
			throws JsonProcessingException {

		log.info("Before sendLibarayEvent");
		// Asynchronous request
		libraryEventProducer.sendLibarayEvent(libraryEvent);

		// Asynchronous Approach2
		libraryEventProducer.sendLibarayEventAsynchronousApproach2(libraryEvent);

		// Synchronous request
		libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);

		log.info("After sendLibarayEvent");

		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}

}

package org.api.microservice.controller;

import org.api.microservice.wrapper.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Movie Info MicroService
@RestController
@RequestMapping("/movies")
public class MovieResourceController {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		return new Movie(movieId, "Test Name");
	}

}

package org.api.microservice.controller;

import java.util.Arrays;

import org.api.microservice.wrapper.Rating;
import org.api.microservice.wrapper.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Rating MicroService
@RestController
@RequestMapping("/ratingdata")
public class RatingDataResourceController {

	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("1234", 4), new Rating("5678", 3)));
		return userRating;
	}

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 4);
	}

}

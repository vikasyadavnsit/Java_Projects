package org.api.microservice.controller;

import java.util.Arrays;

import org.api.microservice.wrapper.Rating;
import org.api.microservice.wrapper.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RatingInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRatings(String userId) {
		log.info("Inside getUserRatings() ");
		return restTemplate.getForObject("http://sprig-boot-learn-3/ratingdata/users/" + userId, UserRating.class);
	}

	private UserRating getFallbackUserRating(String userId) {
		log.info("Inside getFallbackUserRating() ");
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(new Rating("", 0)));
		return userRating;
	}
}
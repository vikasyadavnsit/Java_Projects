package org.api.microservice.controller;

import org.api.microservice.wrapper.CatalogItem;
import org.api.microservice.wrapper.Movie;
import org.api.microservice.wrapper.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieInfo {

	@Autowired
	public RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", threadPoolKey = "movieInfoPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "20"), @HystrixProperty(name = "maxQueueSize", value = "10") })
	public CatalogItem getCatalogItem(Rating rating) {
		log.info("Inside getCatalogItem() ");
		Movie movie = restTemplate.getForObject("http://sprig-boot-learn-2/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getTitle(), movie.getOverview(), rating.getRating());
	}

	private CatalogItem getFallbackCatalogItem(Rating rating) {
		log.info("Inside getFallbackCatalogItem() ");
		return new CatalogItem("Movie Name not found", "", rating.getRating());
	}
}
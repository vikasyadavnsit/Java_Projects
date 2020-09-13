package org.api.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.api.microservice.wrapper.CatalogItem;
import org.api.microservice.wrapper.Movie;
import org.api.microservice.wrapper.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


//Movie Catalog MicroService
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouce {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoverClient;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

//		Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
//		.retrieve().bodyToMono(Movie.class).block();

		// Get all rated movie IDs
		// sprig-boot-learn-3 Synonym to localhost:8083
		UserRating userRating = restTemplate.getForObject("http://sprig-boot-learn-3/ratingdata/users/" + userId,
				UserRating.class);

		// For each Movie ID , call movie Info service and get details
		// sprig-boot-learn-2 Synonym to localhost:8082
		List<CatalogItem> items = userRating.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://sprig-boot-learn-2/movies/" + rating.getMovieId(),
					Movie.class);
			return new CatalogItem(movie.getName(), "Description", rating.getRating());
		}).collect(Collectors.toList());

		return items;
	}

}

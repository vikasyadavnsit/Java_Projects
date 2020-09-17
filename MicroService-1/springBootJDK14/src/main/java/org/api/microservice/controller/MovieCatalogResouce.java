package org.api.microservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.api.microservice.wrapper.CatalogItem;
import org.api.microservice.wrapper.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Movie Catalog MicroService
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouce {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private RatingInfo ratingInfo;

//	@Autowired
// 	private DiscoveryClient discoverClient;

//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	// @HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

//		Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
//		.retrieve().bodyToMono(Movie.class).block();

		// Get all rated movie IDs
		// sprig-boot-learn-3 Synonym to localhost:8083
		UserRating userRating = ratingInfo.getUserRatings(userId);

		// For each Movie ID , call movie Info service and get details
		// sprig-boot-learn-2 Synonym to localhost:8082
		List<CatalogItem> items = userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());

		return items;
	}

	private List<CatalogItem> getFallbackCatalog(@PathVariable String userId) {
		return Arrays.asList(new CatalogItem("No Movie", "", 0));
	}
}

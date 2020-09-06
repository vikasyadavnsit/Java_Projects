package org.api.controller.consume;

import java.util.Arrays;
import java.util.List;

import org.api.java.generics.PostsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/consume")
@CacheConfig(cacheNames = { "getAllPosts" })
public class ConsumeRestWebServiceController {

	@Autowired
	private RestTemplate restTemplate;

	@Cacheable
	@GetMapping("/getAllPosts")
	public List<PostsWrapper> getAllPosts() {
		log.info("Inside ConsumeRestWebServiceController : getAllPosts() ");
		PostsWrapper[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",
				PostsWrapper[].class);
		return Arrays.asList(posts);
	}

	// condition="" control the caching based on the input of the method, unless=""
	// controls on the output of the method
	@CachePut(value = "getAllPosts", condition = "#post > 10 && #post < 15", unless = "#result.size()>90")
	@GetMapping("/updateAllPostCache/{post}")
	public List<PostsWrapper> updateAllPostCache(@PathVariable int post) {
		log.info("Inside ConsumeRestWebServiceController : updateAllPostCache() ");
		PostsWrapper[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",
				PostsWrapper[].class);
		return Arrays.asList(posts);
	}

	@CacheEvict(value = { "getAllPosts" }, allEntries = true)
	@GetMapping("/clearAllPostsCache")
	public void clearAllPostsCache() {
		log.info("Inside ConsumeRestWebServiceController : clearAllPostsCache()");
	}

}

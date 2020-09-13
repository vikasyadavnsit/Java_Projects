package org.api.controller.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/evict")
public class ConsumeRsetWebServiceCacheController {

	@Autowired
	private CacheManager cacheManager;

	@GetMapping("/clearSpecificCacheEntry/{cacheName}/{cacheKey}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void clearSpecificCacheEntry(@PathVariable String cacheName, @PathVariable String cacheKey) {
		log.info("Inside ConsumeRsetWebServiceCacheController : clearSpecificCacheEntry() ");
		cacheManager.getCache(cacheName).evictIfPresent(cacheKey);
	}

	@GetMapping("/clearSpecificCache/{cacheName}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void clearSpecificCache(@PathVariable String cacheName) {
		log.info("Inside ConsumeRsetWebServiceCacheController : clearSpecificCache() ");
		cacheManager.getCache(cacheName).clear();
	}

	@GetMapping("/clearAllCaches")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void clearAllCaches() {
		log.info("Inside ConsumeRsetWebServiceCacheController : clearAllCaches() ");
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}

}
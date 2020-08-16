package com.battleramp.redis.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

	@GetMapping("/setString")
	public void setString(@RequestParam Optional<String> key) {
		if (key.isPresent()) {
			System.out.println(key);
		}
	}
}

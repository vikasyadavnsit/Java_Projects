package com.battleramp.redis;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.battleramp.common.wrapper.AddressWrapper;
import com.battleramp.common.wrapper.PersonWrapper;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

	@Autowired
	private RedisService redisService;

	@GetMapping("/getAllPerson")
	public ResponseEntity<Iterable<PersonWrapper>> getAllPerson() {
		Iterable<PersonWrapper> allPersons = redisService.getAllPerson();
		return ResponseEntity.ok(allPersons);
	}

	@GetMapping("/savePerson")
	public ResponseEntity<String> savePerson(@RequestParam Optional<String> firstName,
			@RequestParam Optional<String> lastName) {
		PersonWrapper savedPerson = null;
		if (firstName.isPresent() && lastName.isPresent()) {
			PersonWrapper newPerson = PersonWrapper.builder().firstname(firstName.get()).lastname(lastName.get())
					.address(AddressWrapper.builder().zipcode("110059").build()).build();
			savedPerson = redisService.savePerson(newPerson);
		}
		return savedPerson == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(savedPerson.getId());
	}
}

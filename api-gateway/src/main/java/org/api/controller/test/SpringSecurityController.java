package org.api.controller.test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.api.repository.UserRepository;
import org.api.wrapper.generic.UserRolesWrapper;
import org.api.wrapper.generic.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/spring-security")
public class SpringSecurityController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/everyone")
	public String all() {
		return "Everyone";
	}

	@GetMapping("/user")
	public String allUser() {
		return "ALL USERS";
	}

	@GetMapping("/admin")
	public String allAdmin() {
		return "ALL ADMINS";
	}

	@GetMapping("/addUser")
	public UserWrapper addUser() {

		UserWrapper user = new UserWrapper();
		user.setUserName("admin" + Math.random());
		user.setPassword("admin");
		user.setActive("Y");
		user.setCreatedOn(new Timestamp(System.currentTimeMillis()));

		UserRolesWrapper user1Role = new UserRolesWrapper();
		user1Role.setActive("Y");
		user1Role.setRole("ADMIN");

		UserRolesWrapper user2Role = new UserRolesWrapper();
		user2Role.setActive("Y");
		user2Role.setRole("USER");

		user.setAuthorities(Arrays.asList(user1Role, user2Role));
		return userRepository.save(user);
	}

	@GetMapping("/getAllUsers")
	@Cacheable(cacheNames = "getAllUsers")
	public List<UserWrapper> getAllUsers() {
		return userRepository.findAll();
	}

}

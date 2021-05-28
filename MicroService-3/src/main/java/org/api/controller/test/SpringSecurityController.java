package org.api.controller.test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.api.configuration.PropertyConfig;
import org.api.repository.UserRepository;
import org.api.wrapper.generic.UserRolesWrapper;
import org.api.wrapper.generic.UserWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/spring-security")
public class SpringSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Environment env;

	@Autowired
	private PropertyConfig apiConfig;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
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

	@RequestMapping("/test")
	public List<UserWrapper> test() throws InterruptedException {
		List<UserWrapper> userList = null;
		try {
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
			UserWrapper userSaved = userRepository.save(user);
			userList = userRepository.findAll();
		} catch (Exception e) {
			logger.error("SQL Exception Occured : " + e.getMessage());
		}
		return userList;
		// return apiConfig.getDefaultMessage() + env.getProperty("app.messages");
	}

}

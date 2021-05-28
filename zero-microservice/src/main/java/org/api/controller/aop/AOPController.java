package org.api.controller.aop;

import java.util.List;

import org.api.services.aop.AOPService;
import org.api.wrapper.generic.PersonWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AOPController {

	private AOPService aopService;

	public AOPController(AOPService aopService) {
		this.aopService = aopService;
	}

	@GetMapping("/getAllUsersNames")
	public List<String> getAllUsersNames() {
		return aopService.getAllUsersNames();
	}

	@GetMapping("/getRandomUserName")
	public String getRandomUserName() {
		return aopService.getRandomUserName();
	}

	@GetMapping("/getAllUserDetails")
	public List<PersonWrapper> getAllUserDetails() {
		return aopService.getAllUserDetails();
	}

}

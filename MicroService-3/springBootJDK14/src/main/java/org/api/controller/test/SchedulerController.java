package org.api.controller.test;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/scheduler")
public class SchedulerController {

	@RequestMapping("/atFixedDelay")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ResponseBody
	public String scheduleAtFixedDelay() {
		return "Scheduler Started Infinitely : At a fixed delay";
	}
	//Schedulers may be already running at specific intervals -- look at the SchedulerService class
}

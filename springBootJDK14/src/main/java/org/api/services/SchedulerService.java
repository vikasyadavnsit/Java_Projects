package org.api.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedulerService {

	// It will execute the following method after 10 minute of finishing the last
	// task
	@Scheduled(fixedDelay = 1000000, initialDelay = 10000)
	public void fixedDelay() {
		log.info("Inside SchedulerService : fixedDelay() Method");
	}

	// It will execute the following method every 10 minute whether the previous task
	// is finished or not
	@Async
	@Scheduled(fixedRate = 1000000, initialDelay = 50000)
	public void fixedRate() {
		log.info("Inside SchedulerService : fixedRate() Method");
	}

	// The cron will run in every 5 second interveral in the first 5 minutes from 12
	// noon to 11:00pm on every last 5th day of the month
	// FORMAT: <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year>
	// <command>
	@Scheduled(cron = "0/5 1-5 12-23 L-5 * *", zone = "GMT+5.30")
	public void cronJobScheduler() {
		log.info("Inside SchedulerService : cronJobScheduler() Method");
	}

	// Spring cron Special Expressions
	@Scheduled(cron = "@hourly")
	public void cronJobSchedulerUsingAnnotations() {
		log.info("Inside SchedulerService : cronJobSchedulerUsingAnnotations() Method");
	}

}

package org.api.services;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import org.api.controller.test.RetryableController;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RetryableServiceImpl implements RetryableService {

	@Override
	public String retryService() throws SQLException {
		RetryableController.attempts.set(RetryableController.attempts.get() + 1);
		log.info("Inside RetryableSerivce : retryService() Method " + RetryableController.attempts.get() + " Time");
		dummyMethod();
		return "i am executed";
	}

	@Override
	public String recoveryService(SQLException e) {
		log.info("Inside RetryableService : recoveryService() Method, After " + +RetryableController.attempts.get()
				+ " Attempts");
		return "i am executed even after 2 attempts";
	}

	void dummyMethod() throws SQLException {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Exception is going to be thrown from RetryableSerivce : dummyMethod() "
				+ RetryableController.attempts.get() + " Time");
		throw new SQLException();
	}

}

package org.api.services;

import java.sql.SQLException;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public interface RetryableService {

	@Retryable(value = { SQLException.class }, maxAttempts = 2, backoff = @Backoff(delay = 3000))
	public String retryService() throws SQLException;

	@Recover
	String recoveryService(SQLException e);
}

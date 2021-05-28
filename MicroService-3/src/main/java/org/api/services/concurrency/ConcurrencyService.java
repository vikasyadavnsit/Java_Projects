package org.api.services.concurrency;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface ConcurrencyService {
	ArrayList<Integer> getLargeIntList() throws Exception;

	ArrayList<Integer> getLargeUnmodifiableIntList() throws Exception;
}

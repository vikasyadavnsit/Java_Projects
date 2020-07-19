package com.battleramp.concurrency.services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface TestService {
	ArrayList<Integer> getLargeIntList() throws Exception;

	ArrayList<Integer> getLargeUnmodifiableIntList() throws Exception;
}

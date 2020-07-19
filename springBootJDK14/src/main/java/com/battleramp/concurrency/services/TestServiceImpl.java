package com.battleramp.concurrency.services;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public ArrayList<Integer> getLargeIntList() throws Exception {
		/*
		 * Another Ways to Generate Integer List
		 * .unordered().collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
		 * .mapToObj(Integer::valueOf).unordered().collect(Collectors.toList());
		 */
		return (ArrayList<Integer>) IntStream.range(1, 1_00_00_000).parallel().boxed().collect(Collectors.toList());
	}

	@Override
	public ArrayList<Integer> getLargeUnmodifiableIntList() throws Exception {
		return (ArrayList<Integer>) IntStream.range(1, 1_00_00_000).parallel().boxed()
				.collect(Collectors.toUnmodifiableList());
	}

}

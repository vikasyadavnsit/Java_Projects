package com.battleramp.concurrency.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.battleramp.concurrency.services.TestService;
import com.battleramp.concurrency.utility.ConcurrentUtil;

@RestController("/concurrency")
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private ConcurrentUtil concurrentUtil;

	@GetMapping("getLargeModifiableIntList")
	public ResponseEntity<ArrayList<Integer>> getLargeModifiableIntList() {
		try {
			return ResponseEntity.ok(testService.getLargeIntList());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("getLargeUnModifiableIntList")
	public ResponseEntity<ArrayList<Integer>> getLargeUnModifiableIntList() {
		try {
			return ResponseEntity.ok(testService.getLargeUnmodifiableIntList());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("sequencialSum")
	public ResponseEntity<String> sequencialSum() {
		try {
			return ResponseEntity.ok(concurrentUtil.sequencialSum());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("parallelSum")
	public ResponseEntity<String> parallelSum() {
		try {
			return ResponseEntity.ok(concurrentUtil.parallelSum());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("printSequencialCharThroughMultipleThreads/{count}")
	public ResponseEntity<String> printSequencialCharThroughMultipleThreads(@PathVariable int count) {
		try {
			return ResponseEntity.ok(concurrentUtil.printSequencialCharThroughMultipleThreads(count));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("threadLocalImplementation")
	public ResponseEntity<String> threadLocalImplementation() {
		try {
			return ResponseEntity.ok(concurrentUtil.threadLocalImplementation());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("oddEvenCountUsingReentrantLock/{count}")
	public ResponseEntity<String> oddEvenCountUsingReentrantLock(@PathVariable("count") int maxCount) {
		try {
			return ResponseEntity.ok(concurrentUtil.oddEvenCountUsingReentrantLock(maxCount));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("readWriteLock")
	public ResponseEntity<String> readWriteLock() {
		try {
			return ResponseEntity.ok(concurrentUtil.readWriteLock());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}

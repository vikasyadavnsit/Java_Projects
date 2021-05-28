package org.api.controller.concurrency;

import java.util.ArrayList;

import org.api.services.concurrency.ConcurrencyService;
import org.api.utility.concurrency.ConcurrentUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/concurrency")
public class ConcurrencyController {

	@Autowired
	private ConcurrencyService testService;

	@Autowired
	private ConcurrentUtility concurrentUtil;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> fallbackHandler() {
		return ResponseEntity.badRequest().body("Bad Request");
	}

	@GetMapping("/getLargeModifiableIntList")
	public ResponseEntity<ArrayList<Integer>> getLargeModifiableIntList() throws Exception {
		return ResponseEntity.ok(testService.getLargeIntList());
	}

	@GetMapping("/getLargeUnModifiableIntList")
	public ResponseEntity<ArrayList<Integer>> getLargeUnModifiableIntList() throws Exception {
		return ResponseEntity.ok(testService.getLargeUnmodifiableIntList());
	}

	@GetMapping("/sequencialSum")
	public ResponseEntity<String> sequencialSum() throws Exception {
		return ResponseEntity.ok(concurrentUtil.sequencialSum());
	}

	@GetMapping("/parallelSum")
	public ResponseEntity<String> parallelSum() throws Exception {
		return ResponseEntity.ok(concurrentUtil.parallelSum());
	}

	@GetMapping("/printSequencialCharThroughMultipleThreads/{count}")
	public ResponseEntity<String> printSequencialCharThroughMultipleThreads(@PathVariable int count)
			throws InterruptedException {
		return ResponseEntity.ok(concurrentUtil.printSequencialCharThroughMultipleThreads(count));
	}

	@GetMapping("/threadLocalImplementation")
	public ResponseEntity<String> threadLocalImplementation() throws Exception {
		return ResponseEntity.ok(concurrentUtil.threadLocalImplementation());
	}

	@GetMapping("/oddEvenCountUsingReentrantLock/{count}")
	public ResponseEntity<String> oddEvenCountUsingReentrantLock(@PathVariable("count") int maxCount) {
		return ResponseEntity.ok(concurrentUtil.oddEvenCountUsingReentrantLock(maxCount));
	}

	@GetMapping("/readWriteLockImplementation")
	public ResponseEntity<String> readWriteLock() {
		return ResponseEntity.ok(concurrentUtil.readWriteLockImplementation());
	}

	@GetMapping("/interruptsImplementation")
	public ResponseEntity<String> interruptsImplementation() throws Exception {
		return ResponseEntity.ok(concurrentUtil.interruptsImplementation());
	}

	@GetMapping("/phaserImplementation")
	public ResponseEntity<String> phaserImplementation() {
		return ResponseEntity.ok(concurrentUtil.phaserImplementation());
	}

	@GetMapping("/forkJoinPoolImplementation")
	public ResponseEntity<String> forkJoinPoolImplementation() throws Exception {
		return ResponseEntity.ok(concurrentUtil.forkJoinPoolImplementation());
	}

	@GetMapping("/completableFutureImplementation")
	public ResponseEntity<String> completableFutureImplementation() throws Exception {
		return ResponseEntity.ok(concurrentUtil.completableFutureImplementation());
	}

	@GetMapping("/longAdderLongAccumulatorImplementation")
	public ResponseEntity<String> longAdderLongAccumulatorImplementation() {
		return ResponseEntity.ok(concurrentUtil.longAdderLongAccumulatorImplementation());
	}

	@GetMapping("/deadLockDetectionImplementation")
	public ResponseEntity<String> deadLockDetectionImplementation() {
		return ResponseEntity.ok(concurrentUtil.deadLockDetectionImplementation());
	}

	@GetMapping("/spinLockImplementation")
	public ResponseEntity<String> spinLockImplementation() {
		return ResponseEntity.ok(concurrentUtil.spinLockImplementation());
	}

	@GetMapping("/semaphoreImplementation/{semaphores}")
	public ResponseEntity<String> semaphoreImplementation(@PathVariable int semaphores) {
		return ResponseEntity.ok(concurrentUtil.semaphoreImplementation(semaphores));
	}

	@GetMapping("/reuseThreadImplementation")
	public ResponseEntity<String> reuseThreadImplementation() {
		return ResponseEntity.ok(concurrentUtil.reuseThreadImplementation());
	}

}

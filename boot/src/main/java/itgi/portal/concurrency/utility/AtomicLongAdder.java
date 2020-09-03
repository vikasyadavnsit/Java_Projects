package itgi.portal.concurrency.utility;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class AtomicLongAdder implements Runnable {
	LongAdder adder;

	public AtomicLongAdder(LongAdder adder) {
		this.adder = adder;
	}

	@Override
	public void run() {
		if (ThreadLocalRandom.current().nextInt(20) % 2 == 0) {
			adder.increment();
		} else {
			adder.add(ThreadLocalRandom.current().nextLong(10));
		}
	}

}


package org.api.utility.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAccumulator;

public class AtomicLongAccumulator implements Runnable {

	private LongAccumulator accumulator;

	public AtomicLongAccumulator(LongAccumulator accumulator) {
		this.accumulator = accumulator;
	}

	@Override
	public void run() {
		accumulator.accumulate(ThreadLocalRandom.current().nextLong(100) + 1);
	}
}

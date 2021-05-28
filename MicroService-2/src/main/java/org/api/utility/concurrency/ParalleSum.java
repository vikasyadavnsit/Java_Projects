package org.api.utility.concurrency;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

public class ParalleSum implements Callable<BigInteger> {

	private BigInteger sum;
	private List<Integer> list;

	ParalleSum(List<Integer> list) {
		this.sum = new BigInteger("0");
		this.list = list;
	}

	@Override
	public BigInteger call() throws Exception {
		for (int i : this.list) {
			Thread.sleep(1);
			sum = sum.add(BigInteger.valueOf(i * 991));
		}
		return sum;
	}

}

package org.api.utility.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadLockDetectionTask implements Runnable {

	@Override
	public void run() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		long[] threadIds = bean.findDeadlockedThreads();
		if (threadIds != null) {
			System.out.println("#########  MXBean Dead Lock Detection Started #######");
			ThreadInfo[] infos = bean.getThreadInfo(threadIds);
			for (ThreadInfo info : infos) {
				if (info != null) {
					for (Thread thread : Thread.getAllStackTraces().keySet()) {
						if (thread.getId() == info.getThreadId()) {
							System.err.println(info.toString().trim());

							for (StackTraceElement ste : thread.getStackTrace()) {
								System.err.println("\t" + ste.toString().trim());
							}
						}
					}
				}
			}
		} else {
			System.out.println("---------- No DeadLock So Far ---------- ");
		}
	}

}

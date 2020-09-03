package itgi.portal.concurrency.utility;

import java.util.concurrent.locks.ReentrantLock;

public class SpinLock extends ReentrantLock {
	
	private static final long serialVersionUID = 1L;

	SpinLock(){
		super();
	}
	
	public void lock(){
		while(!super.tryLock());
	}
	
	public void unlock(){
		super.unlock();
	}
}

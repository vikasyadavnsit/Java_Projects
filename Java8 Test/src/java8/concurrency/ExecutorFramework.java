package java8.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFramework implements Runnable {

	public void run() {
		for (int i = 0; i < 2; i++) {
			try {
				Date t = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
				System.out.println(
						"Execution of " + Thread.currentThread().getName() + " Starts at Time : " + sf.format(t));
				Thread.currentThread().sleep(Math.round(Math.random() * 10 + 50));
				System.out.println("Execution " + Thread.currentThread().getName() + " End at Time : " + sf.format(t));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Runnable r1[] = new ExecutorFramework[200];
		ExecutorService pool = Executors.newFixedThreadPool(20);
		for (Runnable temp : r1) {
			temp = new ExecutorFramework();
			pool.execute(temp);
		}

	}

}

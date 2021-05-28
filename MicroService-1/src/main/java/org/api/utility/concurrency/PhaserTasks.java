package org.api.utility.concurrency;

import java.time.LocalTime;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class PhaserTasks implements Runnable {

	Phaser phaser;

	public PhaserTasks(Phaser phaser) {
		this.phaser = phaser;
		this.phaser.register();
	}

	private void print(String msg) {
		System.out.printf("%-20s: %10s, t=%s, registered=%s, arrived=%s, unarrived=%s phase=%s%n", msg,
				Thread.currentThread().getName(), LocalTime.now(), phaser.getRegisteredParties(),
				phaser.getArrivedParties(), phaser.getUnarrivedParties(), phaser.getPhase());
	}

	@Override
	public void run() {
		boolean activeForMorePhases = true;
		while (activeForMorePhases) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
				print("Before");
				phaser.arriveAndAwaitAdvance();
				if (ThreadLocalRandom.current().nextInt(3) % 2 == 0) {
					print("Unregistered");
					phaser.arriveAndDeregister();
					activeForMorePhases = false;
				} else
					print("After");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

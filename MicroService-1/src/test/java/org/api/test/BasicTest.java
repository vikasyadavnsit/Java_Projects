package org.api.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BasicTest {

	@Test
	public void doNothing() {
		assertTrue("doNothing".equalsIgnoreCase("donothing"));
	}
}

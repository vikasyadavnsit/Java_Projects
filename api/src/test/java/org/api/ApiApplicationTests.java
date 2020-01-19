package org.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
		boolean expected = true;
		boolean actual = true;
		assertEquals(expected, actual);
		fail("We Will Implement it in future");
	}

}

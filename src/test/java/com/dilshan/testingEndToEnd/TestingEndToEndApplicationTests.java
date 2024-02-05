package com.dilshan.testingEndToEnd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class TestingEndToEndApplicationTests {

	Calculator cal = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int a = 10;
		int b = 20;
		//when
		int result = cal.add(a, b);

		//then
		int expected = 30;
		assertThat(result).isEqualTo(expected);
	}

	static class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

}

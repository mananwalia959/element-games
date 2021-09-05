package com.shop.elementgames;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:applcation-test.properties")
class ElementgamesApplicationTests {

	@Test
	void contextLoads() {
		// just an empty test to check if context is loaded
	}

}

package com.shop.elementgames;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ElementgamesApplicationTests {

	@Test
	void contextLoads() {
		
		var result = Pattern.matches("[^\\.]*", "index.html");
		System.out.println(result);
	}
	
	

}

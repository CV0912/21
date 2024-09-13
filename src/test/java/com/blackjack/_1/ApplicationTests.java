package com.blackjack._1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	@GetMapping("/test")
	public String testEndpoint() {
    	return "Controller is working";
	}


}

package com.blackdev.bestCoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BestCoffeeApplication {

	public static void main(String[] args) {

		SpringApplication.run(BestCoffeeApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello!";
	}
}

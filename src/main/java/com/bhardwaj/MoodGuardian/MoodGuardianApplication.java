package com.bhardwaj.MoodGuardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication(scanBasePackages={"com.bhardwaj.MoodGuardian"})
public class MoodGuardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodGuardianApplication.class, args);
	}

	@GetMapping("/")
	public String home(){
		return "Hello! Welcome to MoodGuardian.";
	}
}

package com.bhardwaj.MoodGuardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@SpringBootApplication(scanBasePackages={"com.bhardwaj.MoodGuardian"})
public class MoodGuardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodGuardianApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<Optional<String>> home(){
		return new ResponseEntity<>(Optional.of("Hello! Welcome to MoodGuardian."),HttpStatus.OK);
	}
}

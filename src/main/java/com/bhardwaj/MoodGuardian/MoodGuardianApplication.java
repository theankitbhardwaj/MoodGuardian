package com.bhardwaj.MoodGuardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.bhardwaj.MoodGuardian"})
public class MoodGuardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodGuardianApplication.class, args);
	}
}

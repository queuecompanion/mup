package com.queuecompanion.mup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MupApplication {

	// TODO: seed values with CommandLineRunner https://blog.nimbleways.com/db-migrations-with-liquibase-and-spring-boot/
	// TODO: passwords from configuration file should be environment variables
	public static void main(String[] args) {
		SpringApplication.run(MupApplication.class, args);
	}

	// TODO: github CI/CD, logging config, split into Common / Core / Server / Client projects, Audit
}

package com.hoony.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MicroServiceApplication.class);
		app.run(args);
	}
}

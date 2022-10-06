package com.spring.jpa.h2.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootJpaH2DronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2DronesApplication.class, args);
	}

}

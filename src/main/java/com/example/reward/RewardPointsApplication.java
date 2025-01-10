package com.example.reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
public class RewardPointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardPointsApplication.class, args);
	}

}

package com.pharmacy_recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PharmacyRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyRecommendationApplication.class, args);
	}

}
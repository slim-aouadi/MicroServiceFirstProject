package com.miniprojet.promotion;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class PromotionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionsApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CouponRepository repository) {
		return (args) -> {
		};
	}
}

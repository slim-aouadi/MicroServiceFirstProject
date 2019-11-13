package com.miniprojet.panier;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PanierApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanierApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(PanierRepository repository) {
		return (args) -> {
			repository.findAll().forEach(System.out::println);
		};
	}

}

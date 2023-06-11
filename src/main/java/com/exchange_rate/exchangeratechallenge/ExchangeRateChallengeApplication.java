package com.exchange_rate.exchangeratechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExchangeRateChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateChallengeApplication.class, args);
	}

}
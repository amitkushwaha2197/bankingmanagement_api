package com.bankingmanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BankingManagement {

	public static void main(String[] args) {
		SpringApplication.run(BankingManagement.class, args);
		log.info("LOGS MESSAGE!");
	}

}

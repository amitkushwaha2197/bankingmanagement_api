package com.bankingmanagement;

import org.springframework.boot.SpringApplication;

public class TestBankingmanagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankingManagement::main).with(TestcontainersConfiguration.class).run(args);
	}

}

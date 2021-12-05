package com.person.addressmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.person.addressmgmt")
public class AddressmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressmgmtApplication.class, args);
	}

}

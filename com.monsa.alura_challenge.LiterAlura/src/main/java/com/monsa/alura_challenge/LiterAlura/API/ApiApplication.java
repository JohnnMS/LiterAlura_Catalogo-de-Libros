package com.monsa.alura_challenge.LiterAlura.API;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.monsa.alura_challenge.LiterAlura.API.Principal.Principal;

@SpringBootApplication
@EnableJpaRepositories
public class ApiApplication implements CommandLineRunner {

	private Principal principal;
	
	public ApiApplication(Principal principal) {
		this.principal = principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.run_menu();
	}

}

package com.example.Complexit;

import com.example.Complexit.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ComplexitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplexitApplication.class, args);
	}

}

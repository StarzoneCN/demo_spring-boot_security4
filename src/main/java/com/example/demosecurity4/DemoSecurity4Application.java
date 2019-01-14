package com.example.demosecurity4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@SpringBootApplication
public class DemoSecurity4Application {


	@Bean
	@Primary
	public SessionRegistry sessionRegistry(){
		return new SessionRegistryImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSecurity4Application.class, args);
	}
}

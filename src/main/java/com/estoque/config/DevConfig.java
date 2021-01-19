package com.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estoque.services.EmailService;
import com.estoque.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}

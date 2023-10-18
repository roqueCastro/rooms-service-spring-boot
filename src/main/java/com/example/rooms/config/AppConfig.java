package com.example.rooms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clientRest")
	public RestTemplate registreRestTemplate() {
		return new RestTemplate();
	}
}

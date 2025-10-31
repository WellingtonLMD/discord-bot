package com.wlmd.discord_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfig {
	@Bean
	public RestClient  webClient() {
		return RestClient .builder()
				//.baseUrl("http://localhost:")
				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

}

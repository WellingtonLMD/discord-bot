package com.wlmd.discord_bot.service;

// Teste command service, used for testing

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public String getTestResponse(){
		return "Teste 123";
	}
}

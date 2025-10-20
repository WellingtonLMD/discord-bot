package com.wlmd.discord_bot.service;

// Service where all logic related to the VoiceStateListener listener will be located, still under development

import org.springframework.stereotype.Service;
import com.wlmd.discord_bot.repository.UserActivityRepository;

@Service
public class onGuildVoiceUpdateService {
	private final UserActivityRepository userActivityRepository;

	public onGuildVoiceUpdateService(UserActivityRepository userActivityRepository) {
		this.userActivityRepository = userActivityRepository;
	}
	
	

}

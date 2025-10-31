package com.wlmd.discord_bot.service;

// Service where all logic related to the VoiceStateListener listener will be located, still under development

import org.springframework.stereotype.Service;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import com.wlmd.discord_bot.repository.MemberRepository;
import com.wlmd.discord_bot.repository.MemberActivityRepository;
import com.wlmd.discord_bot.model.MemberModel;
import com.wlmd.discord_bot.model.MemberActivityModel;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

// Service that records a member's entry and exit in any channel and voice
// The idea is to control how much connection time members have on the server.
// TODO: handle possible exceptions
// TODO: Make the entire service more robust overall

@Service
public class GuildVoiceUpdateEventService {
	private final MemberActivityRepository memberActivityRepository;
	private final MemberRepository memberRepository;

	public GuildVoiceUpdateEventService(MemberActivityRepository memberActivityRepository, MemberRepository memberRepository) {
		this.memberActivityRepository = memberActivityRepository;
		this.memberRepository = memberRepository;
	}
	
	public void createSession(GuildVoiceUpdateEvent event) {
		String time = LocalDateTime.now().toString();
		MemberModel user = memberRepository.findByGuild_DiscordGuildIdAndDiscordUserId(event.getGuild().getIdLong(), event.getMember().getIdLong());
		MemberActivityModel activity = memberActivityRepository.findByDiscordGuildIdAndDiscordUserId(event.getGuild().getIdLong() ,user.getDiscordUserId());
		activity.setSessionStart(time);
		memberActivityRepository.save(activity);
		
	}
	
	public void endSession(GuildVoiceUpdateEvent event) {
		String time = LocalDateTime.now().toString();
		MemberModel user = memberRepository.findByGuild_DiscordGuildIdAndDiscordUserId(event.getGuild().getIdLong(), event.getMember().getIdLong());
		MemberActivityModel activity = memberActivityRepository.findByDiscordGuildIdAndDiscordUserId(event.getGuild().getIdLong() ,user.getDiscordUserId());
		activity.setSessionEnd(time);
		activity.setLastSeen(time);
		activity.setTotalTime(setTotalTime(activity.getSessionStart(), activity.getSessionEnd()));
		memberActivityRepository.save(activity);
	}
	
	private Long setTotalTime(String sessionStart, String sessionEnd) {
		LocalDateTime start = LocalDateTime.parse(sessionStart);
		LocalDateTime end = LocalDateTime.parse(sessionEnd);
		Long totalTime = Duration.between(start, end).toMinutes();
	   
		return totalTime;
	}

}

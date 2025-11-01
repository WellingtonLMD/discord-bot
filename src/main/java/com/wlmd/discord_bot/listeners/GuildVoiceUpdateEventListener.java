package com.wlmd.discord_bot.listeners;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import com.wlmd.discord_bot.service.GuildVoiceUpdateEventService;

// Listener that will be responsible for detecting member activity in voice channels and recording their presence on the server.
// currently only logs some user and server data where the action occurs
// TODO: Create logic to generate a report on member attendance

// IMPORTANT : Not all of these tasks should be created here in this Listener. 
// I believe that the correct approach is to create services and auxiliary 
// classes so as not to pollute the code and to separate responsibilities.

@Component
public class GuildVoiceUpdateEventListener extends ListenerAdapter  {

	private final GuildVoiceUpdateEventService guildVoiceUpdateEventService;



	public GuildVoiceUpdateEventListener(GuildVoiceUpdateEventService guildVoiceUpdateEventService) {

		this.guildVoiceUpdateEventService = guildVoiceUpdateEventService;
	}


	@Override
	public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
		AudioChannelUnion joinedChannel = event.getChannelJoined();
		AudioChannelUnion leftChannel = event.getChannelLeft();
		String guildId = event.getGuild().getId();
		String discordUserId = event.getMember().getId();
		String nickName = event.getMember().getNickname();	 
		String serverEffectiveName = event.getMember().getEffectiveName();
		List<Role> roles = event.getMember().getRoles();
		String serverTimeJoined = event.getMember().getTimeJoined().toString();

		String globalName = event.getMember().getUser().getGlobalName();
		String userName = event.getMember().getUser().getName();
		String userEffectiveName = event.getMember().getUser().getEffectiveName();
		String userAvatarId = event.getMember().getUser().getAvatarId();
		String userAvatarUrl = event.getMember().getUser().getAvatarUrl();
		String userTimeCreated = event.getMember().getUser().getTimeCreated().toString();


		if (joinedChannel != null && leftChannel != null) {
			System.out.println("[MOVE] Member changed voice channel:");
			System.out.println(" - Guild ID: " + guildId);
			System.out.println(" - Discord User ID: " + discordUserId);
			System.out.println(" - Nickname: " + nickName);
			System.out.println(" - Server Effective Name: " + serverEffectiveName);
			System.out.println(" - Roles: " + roles);
			System.out.println(" - Server Time Joined: " + serverTimeJoined);
			System.out.println(" - Global Name: " + globalName);
			System.out.println(" - User Name: " + userName);
			System.out.println(" - User Effective Name: " + userEffectiveName);
			System.out.println(" - User Avatar ID: " + userAvatarId);
			System.out.println(" - User Avatar URL: " + userAvatarUrl);
			System.out.println(" - User Time Created: " + userTimeCreated);
			System.out.println();


		} else if (joinedChannel != null) {
			System.out.println("[JOIN] Member joined voice channel Teste 3:");
			System.out.println(" - Guild ID: " + guildId);
			System.out.println(" - Discord User ID: " + discordUserId);
			System.out.println(" - Nickname: " + nickName);
			System.out.println(" - Server Effective Name: " + serverEffectiveName);
			System.out.println(" - Roles: " + roles);
			System.out.println(" - Server Time Joined: " + serverTimeJoined);
			System.out.println(" - Global Name: " + globalName);
			System.out.println(" - User Name: " + userName);
			System.out.println(" - User Effective Name: " + userEffectiveName);
			System.out.println(" - User Avatar ID: " + userAvatarId);
			System.out.println(" - User Avatar URL: " + userAvatarUrl);
			System.out.println(" - User Time Created: " + userTimeCreated);
			System.out.println();
			guildVoiceUpdateEventService.createSession(event);


		} else if (leftChannel != null) {
			System.out.println("[LEAVE] Member left voice channel:");
			System.out.println(" - Guild ID: " + guildId);
			System.out.println(" - Discord User ID: " + discordUserId);
			System.out.println(" - Nickname: " + nickName);
			System.out.println(" - Server Effective Name: " + serverEffectiveName);
			System.out.println(" - Roles: " + roles);
			System.out.println(" - Server Time Joined: " + serverTimeJoined);
			System.out.println(" - Global Name: " + globalName);
			System.out.println(" - User Name: " + userName);
			System.out.println(" - User Effective Name: " + userEffectiveName);
			System.out.println(" - User Avatar ID: " + userAvatarId);
			System.out.println(" - User Avatar URL: " + userAvatarUrl);
			System.out.println(" - User Time Created: " + userTimeCreated);
			System.out.println();
			guildVoiceUpdateEventService.endSession(event);

		}

	}

}

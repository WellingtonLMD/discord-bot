package com.wlmd.discord_bot.listeners;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;

@Component
public class VoiceStateListener extends ListenerAdapter  {
	
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
		     // O membro moveu-se entre dois canais de áudio no mesmo servidor
			    System.out.println("[MOVE] Membro mudou de canal de voz:");
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
		     // O membro entrou em um canal de áudio
			    System.out.println("[MOVE] Membro mudou de canal de voz:");
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

		 } else if (leftChannel != null) {
		     // O membro saiu de um canal de áudio
			    System.out.println("[MOVE] Membro mudou de canal de voz:");
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
		 }

	}

}

package com.wlmd.discord_bot.listeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import com.wlmd.discord_bot.service.AddGuildMemberService;

@Component
public class MemberJoinLeaveEventListener extends ListenerAdapter {
	
	private final AddGuildMemberService addServerUser;
	
	
	
	public MemberJoinLeaveEventListener(AddGuildMemberService addServerUser) {

		this.addServerUser = addServerUser;
	}


	@Override
	public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
		
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
		 addServerUser.addMember(event);
	}

}

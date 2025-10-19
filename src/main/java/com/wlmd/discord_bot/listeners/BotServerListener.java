package com.wlmd.discord_bot.listeners;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotNull;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import com.wlmd.discord_bot.service.LoadServerUsers;

@Component
public class BotServerListener extends ListenerAdapter {
	
	private final LoadServerUsers loadServerUsers;
	
	
	public BotServerListener(LoadServerUsers loadServerUsers) {
		
		this.loadServerUsers = loadServerUsers;
	}

	@Override
	public void onGuildJoin(@NotNull GuildJoinEvent event) {
		
		 String serverName = event.getGuild().getName();
		 String serverId = event.getGuild().getId();
		 String serverJoinedDate = event.getGuild().getTimeCreated().toString();

		 System.out.println("O bot entrou no servidor: " + serverName);
		 System.out.println("ID do servidor: " + serverId);
		 System.out.println("Data: " + serverJoinedDate);
		 System.out.println();
		 loadServerUsers.loadUsers(event);

	}
	
	@Override
	public void onGuildLeave(@NotNull GuildLeaveEvent event) {
		
		 String serverName = event.getGuild().getName();
		 String serverId = event.getGuild().getId();
		 String serverLeaveDate = event.getGuild().getTimeCreated().toString();
		 System.out.println("O bot saiu do servidor: " + serverName);
		 System.out.println("ID do servidor: " + serverId);
		 System.out.println("Data: " + serverLeaveDate);
		 System.out.println();

	}
}

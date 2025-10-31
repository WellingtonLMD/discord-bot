package com.wlmd.discord_bot.service;

import org.springframework.stereotype.Service;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;

import com.wlmd.discord_bot.model.GuildModel;
import com.wlmd.discord_bot.repository.GuildRepository;

// service to create or delete guild entry in the database when the bot enters/leave it
// The code is very raw, but functional.
// TODO: make more robust
@Service
public class AddDeleteGuildService {

	private final GuildRepository guildRepository;

	public AddDeleteGuildService(GuildRepository guildRepository) {

		this.guildRepository = guildRepository;
	}

	// TODO: handle possible exceptions
	public void addGuild(GuildJoinEvent event) {

		Long serverId = event.getGuild().getIdLong();
		String serverName = event.getGuild().getName();		 
		String serverJoinedDate = event.getGuild().getTimeCreated().toString();
		String timeCreated = event.getGuild().getTimeCreated().toString();
		int memberCount =  event.getGuild().getMemberCount();

		GuildModel guild = new GuildModel();

		guild.setDiscordGuildId(serverId);
		guild.setGuildName(serverName);
		guild.setJoinedDate(serverJoinedDate);
		guild.setTimeCreated(timeCreated);
		guild.setMemberCount(memberCount);
		guildRepository.save(guild);
	}

	// TODO: handle possible exceptions
	public void deleteGuild(GuildLeaveEvent event) {
		
		GuildModel guild = guildRepository.findByDiscordGuildId(event.getGuild().getIdLong());
		guildRepository.delete(guild);
	}
}

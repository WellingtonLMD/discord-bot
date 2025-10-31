package com.wlmd.discord_bot.service;

// Service that loads server users into the database

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import java.util.List;
import java.util.stream.Collectors;
import com.wlmd.discord_bot.model.MemberModel;
import com.wlmd.discord_bot.model.MemberRoleModel;
import com.wlmd.discord_bot.model.MemberActivityModel;
import com.wlmd.discord_bot.repository.MemberRepository;
import com.wlmd.discord_bot.repository.GuildRepository;
import com.wlmd.discord_bot.model.GuildModel;

@Service
public class AddGuildMemberService {

	private final MemberRepository userRepository;
	private final GuildRepository guildRepository;

	public AddGuildMemberService(MemberRepository userRepository, GuildRepository guildRepository) {
		this.userRepository = userRepository;
		this.guildRepository = guildRepository;
	}

	// Method triggered every time a new user join the server
	// TODO: Seeing the best way to do this, this is probably not the ideal way, but for now, it works.
	// TODO: Handle exceptions
	public void addMember(GuildMemberJoinEvent event) {
		GuildMemberJoinEvent member = event;
		MemberModel user = new MemberModel();		
		System.out.println("Member: " + member.getMember().getNickname());
		System.out.println("Member Roles: " + member.getMember().getRoles());
		GuildModel guild = guildRepository.findByDiscordGuildId(member.getGuild().getIdLong());
		user.setGuild(guild);
		user.setDiscordUserId(member.getUser().getIdLong()); 
		user.setNickName(member.getMember().getNickname());
		user.setServerEffectiveName(member.getMember().getEffectiveName());

		List<MemberRoleModel> userRoles = member.getMember().getRoles().stream()
				.map(role -> new MemberRoleModel(role.getIdLong(), role.getName(), user))
				.collect(Collectors.toList());

		user.setRoles(userRoles);
		user.setServerTimeJoined(member.getMember().getTimeJoined().toString());
		user.setGlobalName(member.getUser().getGlobalName());
		user.setUserName(member.getUser().getName());
		user.setUserEffectiveName(member.getUser().getEffectiveName());
		user.setUserAvatarId(member.getUser().getAvatarId());
		user.setUserAvatarUrl(member.getUser().getAvatarUrl());
		user.setUserTimeCreated(member.getUser().getTimeCreated().toString());
		
		// For each user registered in the database, add a record to the user activity table.
		// TODO: See if this is the best way to do it.
		//activity.setUser(user);		
		MemberActivityModel activity = new MemberActivityModel(user);		
		user.setMemberActivity(activity);
		
		System.out.println("Saving user: " + member.getMember().getEffectiveName() + " with roles: " + userRoles.size());

		userRepository.save(user);
		System.out.println("The user have been saved to the database!");

	}

}

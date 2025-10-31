package com.wlmd.discord_bot.service;

// Service that loads server users into the database

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.wlmd.discord_bot.model.MemberModel;
import com.wlmd.discord_bot.model.MemberRoleModel;
import com.wlmd.discord_bot.model.MemberActivityModel;
import com.wlmd.discord_bot.repository.MemberRepository;

import jakarta.transaction.Transactional;

import com.wlmd.discord_bot.repository.GuildRepository;
import com.wlmd.discord_bot.model.GuildModel;

@Service
public class LoadGuildMembersService {
	
	private final MemberRepository userRepository;
	private final GuildRepository guildRepository;
	
	public LoadGuildMembersService(MemberRepository userRepository, GuildRepository guildRepository) {

		this.userRepository = userRepository;
		this.guildRepository = guildRepository;
	}
	
	// Method triggered every time the bot enters a server
	public void loadUsers(GuildJoinEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Members Loaded");
			System.out.println("Members: " + members);
			
			List<MemberModel> usersToSave = new ArrayList<>();
			GuildModel userGuild = guildRepository.findByDiscordGuildId(guild.getGuild().getIdLong());
			
			members.forEach(member -> {
				MemberModel user = new MemberModel();				
				System.out.println("Member: " + member.getNickname());
				System.out.println("Member Roles: " + member.getRoles());
				user.setGuild(userGuild);
				user.setDiscordUserId(member.getIdLong());
				user.setNickName(member.getNickname());
				user.setServerEffectiveName(member.getEffectiveName());
				
				user.updateRoles(member.getRoles());
				
				user.setServerTimeJoined(member.getTimeJoined().toString());
				user.setGlobalName(member.getUser().getGlobalName());
				user.setUserName(member.getUser().getName());
				user.setUserEffectiveName(member.getUser().getEffectiveName());
				user.setUserAvatarId(member.getUser().getAvatarId());
				user.setUserAvatarUrl(member.getUser().getAvatarUrl());
				user.setUserTimeCreated(member.getUser().getTimeCreated().toString());
				
				// For each user registered in the database, add a record to the user activity table.
				// TODO: See if this is the best way to do it.
				user.initializeActivity();
				
				usersToSave.add(user);
				System.out.println("Saving user: " + member.getEffectiveName() + " with roles: " + user.getRoles().size());
				
		});
			userRepository.saveAll(usersToSave);
			System.out.println("All members have been saved to the database!");
		});
		
		
	}
	
	// Method triggered by a slash command OLD VERSION
	// TODO : Currently, when the method is triggered, it does what was intended, but if a user is already registered for the same guild in the database, it throws an exception. The intention is to update the user's data if the user already exists and if the data has changed.
	public void loadUsersOld(SlashCommandInteractionEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Members Loaded");
			System.out.println("Members: " + members);
			
			GuildModel userGuild = guildRepository.findByDiscordGuildId(guild.getGuild().getIdLong());
			
			List<MemberModel> existingUsers = userRepository.findAllByGuild_DiscordGuildId(guild.getIdLong());
			Map<Long, MemberModel> existingUsersMap = existingUsers.stream()
			    .collect(Collectors.toMap(MemberModel::getDiscordUserId, user -> user));
			
			List<MemberModel> usersToSave = new ArrayList<>();
			
			members.forEach(member -> {
				MemberModel user = existingUsersMap.getOrDefault(member.getIdLong(), new MemberModel());				
				System.out.println("Member: " + member.getNickname());
				System.out.println("Member Roles: " + member.getRoles());
				user.setGuild(userGuild);
				user.setDiscordUserId(member.getIdLong());
				user.setNickName(member.getNickname());
				user.setServerEffectiveName(member.getEffectiveName());
				
				List<MemberRoleModel> userRoles = member.getRoles().stream()
						.map(role -> new MemberRoleModel(role.getIdLong(), role.getName(), user))
						.collect(Collectors.toList());
				
				user.setRoles(userRoles);
				user.setServerTimeJoined(member.getTimeJoined().toString());
				user.setGlobalName(member.getUser().getGlobalName());
				user.setUserName(member.getUser().getName());
				user.setUserEffectiveName(member.getUser().getEffectiveName());
				user.setUserAvatarId(member.getUser().getAvatarId());
				user.setUserAvatarUrl(member.getUser().getAvatarUrl());
				user.setUserTimeCreated(member.getUser().getTimeCreated().toString());
				
				usersToSave.add(user);
				System.out.println("Saving user: " + member.getEffectiveName() + " with roles: " + userRoles.size());
				
		});
			userRepository.saveAll(usersToSave);
			System.out.println("All members have been saved to the database!");
		});
		
		
	}
	

	// Version of the previous method proposed by chat GPT, the comments on the method are authored by him.
	// I believe it is not the ideal solution, but it works, and will be used until the previous method is updated.
	public void loadUsersOld2(SlashCommandInteractionEvent event) {
	    var guild = event.getGuild();
	    guild.loadMembers().onSuccess(members -> {
	    	System.out.println("Members loaded: " + members.size());
	    	
	    	GuildModel userGuild = guildRepository.findByDiscordGuildId(guild.getIdLong());
	    	
	        // Searches for all existing users in the database
	        //List<UserModel> existingUsers = userRepository.findAllByGuildId(guild.getIdLong());
	        
	        List<MemberModel> existingUsers = userRepository.findAllByGuild_DiscordGuildIdWithRoles(guild.getIdLong());

	        // Creates a lookup map by discordUserId
	        Map<Long, MemberModel> existingUsersMap = existingUsers.stream()
	                .collect(Collectors.toMap(MemberModel::getDiscordUserId, user -> user));

	        List<MemberModel> usersToSave = new ArrayList<>();

	        members.forEach(member -> {
	        	// Search for existing user
	            MemberModel user = existingUsersMap.get(member.getIdLong());
	            boolean isNewUser = false;

	            if (user == null) {
	                user = new MemberModel();
	                user.setGuild(userGuild);
	                user.setDiscordUserId(member.getIdLong());
	                isNewUser = true;
	            }

	            // Updates the fields (always updates)
	            user.setNickName(member.getNickname());
	            user.setServerEffectiveName(member.getEffectiveName());
	            user.setServerTimeJoined(member.getTimeJoined().toString());
	            user.setGlobalName(member.getUser().getGlobalName());
	            user.setUserName(member.getUser().getName());
	            user.setUserEffectiveName(member.getUser().getEffectiveName());
	            user.setUserAvatarId(member.getUser().getAvatarId());
	            user.setUserAvatarUrl(member.getUser().getAvatarUrl());
	            user.setUserTimeCreated(member.getUser().getTimeCreated().toString());

	            // Update roles
	            
	            final MemberModel finalUser = user;
	            
	            List<String> currentRoleNames = user.getRoles().stream()
	            	    .map(MemberRoleModel::getRoleName)
	            	    .toList();

	            	List<Role> discordRoles = member.getRoles();

	            	boolean rolesChanged = discordRoles.size() != currentRoleNames.size() ||
	            	    !discordRoles.stream().map(Role::getName).toList().equals(currentRoleNames);

	            	if (rolesChanged) {
	            	    List<MemberRoleModel> userRoles = discordRoles.stream()
	            	        .map(role -> new MemberRoleModel(role.getIdLong(), role.getName(), finalUser))
	            	        .collect(Collectors.toList());
	            	    user.setRoles(userRoles);
	            	}


				// For each user registered in the database, add a record to the user activity table.
				// TODO: See if this is the best way to do it.
				//activity.setUser(user);		
				MemberActivityModel activity = new MemberActivityModel(user);		
				user.setMemberActivity(activity);
				
	            usersToSave.add(user);
	            System.out.println((isNewUser ? "Inserting new" : "Updating") + 
                        " user: " + member.getEffectiveName());
	        });

	        // Save everything (JPA will perform insert/update as necessary)
	        userRepository.saveAll(usersToSave);
	        System.out.println("All users have been processed and saved!");
	    });
	}
	
	// Version of the previous method proposed by chat GPT, the comments on the method are authored by him.
	// I believe it is not the ideal solution, but it works, and will be used until the previous method is updated.
	@Transactional
	public void loadUsers(SlashCommandInteractionEvent event) {
	    var guild = event.getGuild();

	    guild.loadMembers().onSuccess(members -> {
	        GuildModel userGuild = guildRepository.findByDiscordGuildId(guild.getIdLong());
	        List<MemberModel> existingUsers = userRepository.findAllByGuild_DiscordGuildIdWithRoles(guild.getIdLong());
	        Map<Long, MemberModel> userMap = existingUsers.stream()
	                .collect(Collectors.toMap(MemberModel::getDiscordUserId, u -> u));

	        for (Member member : members) {
	            MemberModel user = userMap.computeIfAbsent(member.getIdLong(), id -> {
	                MemberModel newUser = new MemberModel();
	                newUser.setGuild(userGuild);
	                newUser.setDiscordUserId(id);
	                return newUser;
	            });

	            // Update basic data
	            user.setNickName(member.getNickname());
	            user.setServerEffectiveName(member.getEffectiveName());
	            user.setGlobalName(member.getUser().getGlobalName());
	            user.setUserName(member.getUser().getName());
	            user.setUserAvatarUrl(member.getUser().getAvatarUrl());
	            user.setUserTimeCreated(member.getUser().getTimeCreated().toString());

	            // Updates roles with the encapsulated method
	            user.updateRoles(member.getRoles());

	            // Create activity if it does not already exist
	            if (user.getMemberActivity() == null) {
	                user.setMemberActivity(new MemberActivityModel(user));
	            }
	        }

	        userRepository.saveAll(userMap.values());
	        System.out.println("All users have been processed and saved!");
	    });
	}

}

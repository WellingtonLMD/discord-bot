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
import com.wlmd.discord_bot.model.UserModel;
import com.wlmd.discord_bot.model.UserRole;
import com.wlmd.discord_bot.repository.UserRepository;

@Service
public class LoadServerUsers {
	
	private final UserRepository userRepository;
	
	public LoadServerUsers(UserRepository userRepository) {

		this.userRepository = userRepository;
	}
	
	// Method triggered every time the bot enters a server
	public void loadUsers(GuildJoinEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Members Loaded");
			System.out.println("Members: " + members);
			
			List<UserModel> usersToSave = new ArrayList<>();
			
			members.forEach(member -> {
				UserModel user = new UserModel();				
				System.out.println("Member: " + member.getNickname());
				System.out.println("Member Roles: " + member.getRoles());
				user.setGuildId(member.getGuild().getIdLong());
				user.setDiscordUserId(member.getIdLong());
				user.setNickName(member.getNickname());
				user.setServerEffectiveName(member.getEffectiveName());
				
				List<UserRole> userRoles = member.getRoles().stream()
						.map(role -> new UserRole(role.getIdLong(), role.getName(), user))
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
	
	// Method triggered by a slash command OLD VERSION
	// TODO : Currently, when the method is triggered, it does what was intended, but if a user is already registered for the same guild in the database, it throws an exception. The intention is to update the user's data if the user already exists and if the data has changed.
	public void loadUsersOld(SlashCommandInteractionEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Members Loaded");
			System.out.println("Members: " + members);
			
			
			List<UserModel> existingUsers = userRepository.findAllByGuildId(guild.getIdLong());
			Map<Long, UserModel> existingUsersMap = existingUsers.stream()
			    .collect(Collectors.toMap(UserModel::getDiscordUserId, user -> user));
			
			List<UserModel> usersToSave = new ArrayList<>();
			
			members.forEach(member -> {
				UserModel user = existingUsersMap.getOrDefault(member.getIdLong(), new UserModel());				
				System.out.println("Member: " + member.getNickname());
				System.out.println("Member Roles: " + member.getRoles());
				user.setGuildId(member.getGuild().getIdLong());
				user.setDiscordUserId(member.getIdLong());
				user.setNickName(member.getNickname());
				user.setServerEffectiveName(member.getEffectiveName());
				
				List<UserRole> userRoles = member.getRoles().stream()
						.map(role -> new UserRole(role.getIdLong(), role.getName(), user))
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
	public void loadUsers(SlashCommandInteractionEvent event) {
	    var guild = event.getGuild();
	    guild.loadMembers().onSuccess(members -> {
	    	System.out.println("Members loaded: " + members.size());

	        // Searches for all existing users in the database
	        //List<UserModel> existingUsers = userRepository.findAllByGuildId(guild.getIdLong());
	        
	        List<UserModel> existingUsers = userRepository.findAllByGuildIdWithRoles(guild.getIdLong());

	        // Creates a lookup map by discordUserId
	        Map<Long, UserModel> existingUsersMap = existingUsers.stream()
	                .collect(Collectors.toMap(UserModel::getDiscordUserId, user -> user));

	        List<UserModel> usersToSave = new ArrayList<>();

	        members.forEach(member -> {
	        	// Search for existing user
	            UserModel user = existingUsersMap.get(member.getIdLong());
	            boolean isNewUser = false;

	            if (user == null) {
	                user = new UserModel();
	                user.setGuildId(guild.getIdLong());
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
	            
	            final UserModel finalUser = user;
	            
	            List<String> currentRoleNames = user.getRoles().stream()
	            	    .map(UserRole::getRoleName)
	            	    .toList();

	            	List<Role> discordRoles = member.getRoles();

	            	boolean rolesChanged = discordRoles.size() != currentRoleNames.size() ||
	            	    !discordRoles.stream().map(Role::getName).toList().equals(currentRoleNames);

	            	if (rolesChanged) {
	            	    List<UserRole> userRoles = discordRoles.stream()
	            	        .map(role -> new UserRole(role.getIdLong(), role.getName(), finalUser))
	            	        .collect(Collectors.toList());
	            	    user.setRoles(userRoles);
	            	}


	            usersToSave.add(user);
	            System.out.println((isNewUser ? "Inserting new" : "Updating") + 
                        " user: " + member.getEffectiveName());
	        });

	        // Save everything (JPA will perform insert/update as necessary)
	        userRepository.saveAll(usersToSave);
	        System.out.println("All users have been processed and saved!");
	    });
	}


}

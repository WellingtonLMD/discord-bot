package com.wlmd.discord_bot.service;

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

	public void loadUsers(GuildJoinEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Membros Carregados");
			System.out.println("Membros: " + members);
			
			List<UserModel> usersToSave = new ArrayList<>();
			
			members.forEach(member -> {
				UserModel user = new UserModel();				
				System.out.println("Membros: " + member.getNickname());
				System.out.println("Membros: " + member.getRoles());
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
				System.out.println("Salvando usuário: " + member.getEffectiveName() + " com roles: " + userRoles.size());
				
		});
			userRepository.saveAll(usersToSave);
			System.out.println("Todos os membros foram salvos no banco!");
		});
		
		
	}
	
	public void loadUsersOld(SlashCommandInteractionEvent guild) {
		guild.getGuild().loadMembers().onSuccess(members ->{
			System.out.println("Membros Carregados");
			System.out.println("Membros: " + members);
			
			
			List<UserModel> existingUsers = userRepository.findAllByGuildId(guild.getIdLong());
			Map<Long, UserModel> existingUsersMap = existingUsers.stream()
			    .collect(Collectors.toMap(UserModel::getDiscordUserId, user -> user));
			
			List<UserModel> usersToSave = new ArrayList<>();
			
			members.forEach(member -> {
				UserModel user = existingUsersMap.getOrDefault(member.getIdLong(), new UserModel());				
				System.out.println("Membros: " + member.getNickname());
				System.out.println("Membros: " + member.getRoles());
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
				System.out.println("Salvando usuário: " + member.getEffectiveName() + " com roles: " + userRoles.size());
				
		});
			userRepository.saveAll(usersToSave);
			System.out.println("Todos os membros foram salvos no banco!");
		});
		
		
	}
	

	public void loadUsers(SlashCommandInteractionEvent event) {
	    var guild = event.getGuild();
	    guild.loadMembers().onSuccess(members -> {
	        System.out.println("🔄 Membros carregados: " + members.size());

	        // Busca todos os usuários existentes do banco
	        //List<UserModel> existingUsers = userRepository.findAllByGuildId(guild.getIdLong());
	        
	        List<UserModel> existingUsers = userRepository.findAllByGuildIdWithRoles(guild.getIdLong());

	        // Cria um mapa de lookup por discordUserId
	        Map<Long, UserModel> existingUsersMap = existingUsers.stream()
	                .collect(Collectors.toMap(UserModel::getDiscordUserId, user -> user));

	        List<UserModel> usersToSave = new ArrayList<>();

	        members.forEach(member -> {
	            // Busca usuário existente
	            UserModel user = existingUsersMap.get(member.getIdLong());
	            boolean isNewUser = false;

	            if (user == null) {
	                user = new UserModel();
	                user.setGuildId(guild.getIdLong());
	                user.setDiscordUserId(member.getIdLong());
	                isNewUser = true;
	            }

	            // Atualiza os campos (sempre atualiza)
	            user.setNickName(member.getNickname());
	            user.setServerEffectiveName(member.getEffectiveName());
	            user.setServerTimeJoined(member.getTimeJoined().toString());
	            user.setGlobalName(member.getUser().getGlobalName());
	            user.setUserName(member.getUser().getName());
	            user.setUserEffectiveName(member.getUser().getEffectiveName());
	            user.setUserAvatarId(member.getUser().getAvatarId());
	            user.setUserAvatarUrl(member.getUser().getAvatarUrl());
	            user.setUserTimeCreated(member.getUser().getTimeCreated().toString());

	            // Atualiza roles
	            
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
	            System.out.println((isNewUser ? "🆕 Inserindo novo" : "✏️ Atualizando") + 
	                               " usuário: " + member.getEffectiveName());
	        });

	        // Salva tudo (JPA vai fazer insert/update conforme necessário)
	        userRepository.saveAll(usersToSave);
	        System.out.println("✅ Todos os usuários foram processados e salvos!");
	    });
	}


}

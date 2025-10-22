package com.wlmd.discord_bot.repository;

// User model repository

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wlmd.discord_bot.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
	//Optional<UserModel> findByGuildIdAndDiscordUserId(Long guildId, Long discordUserId);
	UserModel findByGuild_GuildIdAndDiscordUserId(Long guildId, Long discordUserId);
	
	List<UserModel> findAllByGuild_GuildId(Long guildId);
	
	@Query("""
		       SELECT DISTINCT u 
		       FROM UserModel u 
		       LEFT JOIN FETCH u.roles 
		       WHERE u.guild.guildId = :guildId
		       """)
	List<UserModel> findAllByGuild_GuildIdWithRoles(@Param("guildId") Long guildId);

}

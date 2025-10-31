package com.wlmd.discord_bot.repository;

// User model repository

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wlmd.discord_bot.model.MemberModel;

public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
	//Optional<UserModel> findByGuildIdAndDiscordUserId(Long guildId, Long discordUserId);
	MemberModel findByGuild_DiscordGuildIdAndDiscordUserId(Long discordGuildId, Long discordUserId);
	
	List<MemberModel> findAllByGuild_DiscordGuildId(Long discordGuildId);
	
	@Query("""
		       SELECT DISTINCT u 
		       FROM MemberModel u 
		       LEFT JOIN FETCH u.roles 
		       WHERE u.guild.discordGuildId = :discordGuildId
		       """)
	List<MemberModel> findAllByGuild_DiscordGuildIdWithRoles(@Param("discordGuildId") Long discordGuildId);

}

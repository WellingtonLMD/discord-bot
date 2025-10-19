package com.wlmd.discord_bot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wlmd.discord_bot.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
	Optional<UserModel> findByGuildIdAndDiscordUserId(Long guildId, Long discordUserId);
	List<UserModel> findAllByGuildId(Long guildId);
	
    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.roles WHERE u.guildId = :guildId")
    List<UserModel> findAllByGuildIdWithRoles(@Param("guildId") Long guildId);

}

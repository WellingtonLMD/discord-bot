package com.wlmd.discord_bot.repository;

// Repository of user activity model

import org.springframework.data.jpa.repository.JpaRepository;
import com.wlmd.discord_bot.model.MemberActivityModel;

public interface MemberActivityRepository extends JpaRepository<MemberActivityModel, Integer> {
	MemberActivityModel findByGuildIdAndDiscordUserId(Long guildId, Long discordUserId);
}

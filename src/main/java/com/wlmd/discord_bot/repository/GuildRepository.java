package com.wlmd.discord_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wlmd.discord_bot.model.GuildModel;

public interface GuildRepository extends JpaRepository <GuildModel, Integer> {
	GuildModel findByDiscordGuildId(Long discordGuildId);
}

package com.wlmd.discord_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wlmd.discord_bot.model.UserActivityModel;

public interface UserActivityRepository extends JpaRepository<UserActivityModel, Integer> {
	
}

package com.wlmd.discord_bot.repository;

// Repository of user activity model

import org.springframework.data.jpa.repository.JpaRepository;
import com.wlmd.discord_bot.model.UserActivityModel;

public interface UserActivityRepository extends JpaRepository<UserActivityModel, Integer> {
	
}

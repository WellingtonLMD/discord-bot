package com.wlmd.discord_bot.repository;

// Repository of user activity model

import org.springframework.data.jpa.repository.JpaRepository;
import com.wlmd.discord_bot.model.MemberActivityModel;

public interface UserActivityRepository extends JpaRepository<MemberActivityModel, Integer> {
	
}

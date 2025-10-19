package com.wlmd.discord_bot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		name = "UserActivity",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"discordUserId", "guildId"})
		}
)
public class UserActivityModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long userActivityId;	
	
	@Column(nullable = false)
	private Long discordUserId;
		
	@Column(nullable = false)
	private Long guildId;
	
	@Column(nullable = false)
	private String nickName;
	
	@Column(nullable = true)
	private String lastSeen;
	
	@Column(nullable = true)
	private String sessionStart;
	
	@Column(nullable = true)
	private String sessionEnd;
	
	@Column(nullable = true)
	private int totalTime;

	public UserActivityModel(Long userActivityId, Long discordUserId, Long guildId, String nickName, String lastSeen,
			String sessionStart, String sessionEnd, int totalTime) {
	
		this.userActivityId = userActivityId;
		this.discordUserId = discordUserId;
		this.guildId = guildId;
		this.nickName = nickName;
		this.lastSeen = lastSeen;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.totalTime = totalTime;
	}

	public Long getUserActivityId() {
		return userActivityId;
	}

	public void setUserActivityId(Long userActivityId) {
		this.userActivityId = userActivityId;
	}

	public Long getDiscordUserId() {
		return discordUserId;
	}

	public void setDiscordUserId(Long discordUserId) {
		this.discordUserId = discordUserId;
	}

	public Long getGuildId() {
		return guildId;
	}

	public void setGuildId(Long guildId) {
		this.guildId = guildId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(String sessionStart) {
		this.sessionStart = sessionStart;
	}

	public String getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(String sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	
	
}

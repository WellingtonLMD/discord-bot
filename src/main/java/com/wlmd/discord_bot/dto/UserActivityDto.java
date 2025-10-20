package com.wlmd.discord_bot.dto;

// TODO: Not currently in use, check if there is a need for it.

public class UserActivityDto {
	
	private Long userActivityId;
	private Long guildId;
	private String displayName;
	private String lastSeen;
	private String sessionStart;
	private String sessionEnd;
	private int totalTime;

	public UserActivityDto(Long userActivityId, Long guildId, String displayName, String lastSeen,
			String sessionStart, String sessionEnd, int totalTime) {

		this.userActivityId = userActivityId;
		this.guildId = guildId;
		this.displayName = displayName;
		this.lastSeen = lastSeen;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.totalTime = totalTime;
	}
	
	public UserActivityDto() {}

	public Long getUserActivityId() {
		return userActivityId;
	}

	public void setUserActivityId(Long userActivityId) {
		this.userActivityId = userActivityId;
	}

	public Long getGuildId() {
		return guildId;
	}

	public void setGuildId(Long guildId) {
		this.guildId = guildId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	};
	
}

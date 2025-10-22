package com.wlmd.discord_bot.model;

// The model representing the user's activity log (presence) on the server

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import com.wlmd.discord_bot.model.UserModel;

@Entity
@Table(
		name = "UserActivity",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"discordUserId", "guildId"})
		}
)
public class UserActivityModel {
	
	// Defines the owning side of the one-to-one relationship with UserModel.
	// Each UserActivityModel is linked to exactly one UserModel via the user_id foreign key.
	// The activity record must be created and linked manually when registering a new user.
	// TODO: See if this is the best way to do it.
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserModel user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long userActivityId;	
	
	@Column(nullable = false)
	private Long discordUserId;
		
	@Column(nullable = false)
	private Long guildId;
	
	@Column(nullable = true)
	private String nickName;
	
	@Column(nullable = true)
	private String lastSeen;
	
	@Column(nullable = true)
	private String sessionStart;
	
	@Column(nullable = true)
	private String sessionEnd;
	
	@Column(nullable = true)
	private int totalTime;
	
	public UserActivityModel() {}

	public UserActivityModel(UserModel user, Long userActivityId, Long discordUserId, Long guildId, String nickName,
			String lastSeen, String sessionStart, String sessionEnd, int totalTime) {

		this.user = user;
		this.userActivityId = userActivityId;
		this.discordUserId = discordUserId;
		this.guildId = guildId;
		this.nickName = nickName;
		this.lastSeen = lastSeen;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.totalTime = totalTime;
	}
	
	public UserActivityModel(UserModel user) {
	    this.user = user;
	    this.discordUserId = user.getDiscordUserId();
	    this.guildId = user.getGuild().getGuildId();
	    this.totalTime = 0;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	
	
}

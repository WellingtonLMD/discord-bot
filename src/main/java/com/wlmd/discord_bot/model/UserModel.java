package com.wlmd.discord_bot.model;

// The model representing server users

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import net.dv8tion.jda.api.entities.Role;
import com.wlmd.discord_bot.model.UserActivityModel;


@Entity
@Table(
	    name = "Users",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"guildId", "discordUserId"})
	)
public class UserModel {
	
	// For each user registered in the database, add a record to the user activity table.
	// TODO: See if this is the best way to do it.
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private UserActivityModel userActivity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int userId;
	
	@Column(nullable = false)
	private Long guildId;
		
	@Column(nullable = false)
	private Long discordUserId;
	
	@Column(nullable = true)
	private String nickName;
	
	@Column(nullable = true)
	private String serverEffectiveName;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();
	
	@Column(nullable = true)
	private String serverTimeJoined;
	
	@Column(nullable = true)
	private String globalName;
			
	@Column(nullable = true)
	private String userName;
	
	@Column(nullable = true)
	private String userEffectiveName;
	
	@Column(nullable = true)
	private String userAvatarId;
			
	@Column(nullable = true)
	private String userAvatarUrl;
	
	@Column(nullable = true)
	private String userTimeCreated;

	public UserModel() {}
	
	

	public UserModel(UserActivityModel userActivity, int userId, Long guildId, Long discordUserId, String nickName,
			String serverEffectiveName, List<UserRole> roles, String serverTimeJoined, String globalName,
			String userName, String userEffectiveName, String userAvatarId, String userAvatarUrl,
			String userTimeCreated) {

		this.userActivity = userActivity;
		this.userId = userId;
		this.guildId = guildId;
		this.discordUserId = discordUserId;
		this.nickName = nickName;
		this.serverEffectiveName = serverEffectiveName;
		this.roles = roles;
		this.serverTimeJoined = serverTimeJoined;
		this.globalName = globalName;
		this.userName = userName;
		this.userEffectiveName = userEffectiveName;
		this.userAvatarId = userAvatarId;
		this.userAvatarUrl = userAvatarUrl;
		this.userTimeCreated = userTimeCreated;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Long getGuildId() {
		return guildId;
	}

	public void setGuildId(Long guildId) {
		this.guildId = guildId;
	}

	public Long getDiscordUserId() {
		return discordUserId;
	}

	public void setDiscordUserId(Long discordUserId) {
		this.discordUserId = discordUserId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getServerEffectiveName() {
		return serverEffectiveName;
	}

	public void setServerEffectiveName(String serverEffectiveName) {
		this.serverEffectiveName = serverEffectiveName;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getServerTimeJoined() {
		return serverTimeJoined;
	}

	public void setServerTimeJoined(String serverTimeJoined) {
		this.serverTimeJoined = serverTimeJoined;
	}

	public String getGlobalName() {
		return globalName;
	}

	public void setGlobalName(String globalName) {
		this.globalName = globalName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEffectiveName() {
		return userEffectiveName;
	}

	public void setUserEffectiveName(String userEffectiveName) {
		this.userEffectiveName = userEffectiveName;
	}

	public String getUserAvatarId() {
		return userAvatarId;
	}

	public void setUserAvatarId(String userAvatarId) {
		this.userAvatarId = userAvatarId;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public String getUserTimeCreated() {
		return userTimeCreated;
	}

	public void setUserTimeCreated(String userTimeCreated) {
		this.userTimeCreated = userTimeCreated;
	}

	public UserActivityModel getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivityModel userActivity) {
		this.userActivity = userActivity;
	}
	
		
}

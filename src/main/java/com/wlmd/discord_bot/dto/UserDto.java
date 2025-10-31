package com.wlmd.discord_bot.dto;

//TODO: Not currently in use, check if there is a need for it.

import java.util.ArrayList;
import java.util.List;

import com.wlmd.discord_bot.dto.UserRoleDto;

public class UserDto {
	
	private Long userId;
	private Long guildId;
	private Long discordUserId;
	private String nickName;
	private String serverEffectiveName;
	private List<UserRoleDto> roles = new ArrayList<>();
	private String serverTimeJoined;
	private String globalName;
	private String userName;
	private String userEffectiveName;
	private String userAvatarId;
	private String userAvatarUrl;
	private String userTimeCreated;

	public UserDto(Long userId, Long guildId, Long discordUserId, String nickName, String serverEffectiveName,
			List<UserRoleDto> roles, String serverTimeJoined, String globalName, String userName, String userEffectiveName,
			String userAvatarId, String userAvatarUrl, String userTimeCreated) {

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public List<UserRoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleDto> roles) {
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
	
		
}


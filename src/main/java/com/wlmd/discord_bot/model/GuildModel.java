package com.wlmd.discord_bot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.wlmd.discord_bot.model.MemberModel;

@Entity
public class GuildModel {
	
	@OneToMany(mappedBy ="guild", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberModel> users = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	
	@Column(nullable = false)
	private long discordGuildId;
	
	@Column(nullable = false)
	private String guildName;
	
	@Column(nullable = true)
	private String joinedDate;
	
	@Column(nullable = true)
	private String timeCreated;
	
	@Column(nullable = true)
	private int memberCount;

	public GuildModel() {}
	
	public GuildModel(int id, long discordGuildId, String guildName, String joinedDate, String timeCreated, int memberCount) {
		
		this.id = id;
		this.discordGuildId = discordGuildId;
		this.guildName = guildName;
		this.joinedDate = joinedDate;
		this.timeCreated = timeCreated;
		this.memberCount = memberCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDiscordGuildId() {
		return discordGuildId;
	}

	public void setDiscordGuildId(long discordGuildId) {
		this.discordGuildId = discordGuildId;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	
	
}

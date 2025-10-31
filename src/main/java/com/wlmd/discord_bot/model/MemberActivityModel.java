package com.wlmd.discord_bot.model;

// The model representing the user's activity log (presence) on the server

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import com.wlmd.discord_bot.model.MemberModel;
import com.wlmd.discord_bot.model.GuildModel;

@Entity
@Table(
		name = "MemberActivity",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"discordUserId", "discordGuildId"})
		}
)
public class MemberActivityModel {
	
	// Defines the owning side of the one-to-one relationship with UserModel.
	// Each UserActivityModel is linked to exactly one UserModel via the user_id foreign key.
	// The activity record must be created and linked manually when registering a new user.
	// TODO: See if this is the best way to do it.
	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private MemberModel member;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long memberActivityId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guild_id", nullable = false)
	private GuildModel guild;
	
	@Column(nullable = false)
	private Long discordUserId;
		
	@Column(nullable = false)
	private Long discordGuildId;
	
	@Column(nullable = true)
	private String nickName;
	
	@Column(nullable = true)
	private String lastSeen;
	
	@Column(nullable = true)
	private String sessionStart;
	
	@Column(nullable = true)
	private String sessionEnd;
	
	@Column(nullable = true)
	private Long totalTime;
	
	public MemberActivityModel() {}

	public MemberActivityModel(MemberModel member, Long memberActivityId, Long discordUserId, Long discordGuildId, String nickName,
			String lastSeen, String sessionStart, String sessionEnd, Long totalTime) {

		this.member = member;
		this.memberActivityId = memberActivityId;
		this.discordUserId = discordUserId;
		this.discordGuildId = discordGuildId;
		this.nickName = nickName;
		this.lastSeen = lastSeen;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.totalTime = totalTime;
	}
	
	public MemberActivityModel(MemberModel user) {
	    this.member = user;
	    this.nickName = user.getNickName();
	    this.guild = user.getGuild();
	    this.discordUserId = user.getDiscordUserId();
	    this.discordGuildId = user.getGuild().getDiscordGuildId();
	    this.totalTime = 0L;
	}

	public Long getMemberActivityId() {
		return memberActivityId;
	}

	public void setMemberActivityId(Long userActivityId) {
		this.memberActivityId = userActivityId;
	}

	public Long getDiscordUserId() {
		return discordUserId;
	}

	public void setDiscordUserId(Long discordUserId) {
		this.discordUserId = discordUserId;
	}

	public Long getDiscordGuildId() {
		return discordGuildId;
	}

	public void setDiscordGuildId(Long discordGuildId) {
		this.discordGuildId = discordGuildId;
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

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public MemberModel getMember() {
		return member;
	}

	public void setMember(MemberModel member) {
		this.member = member;
	}
	
	
	
}

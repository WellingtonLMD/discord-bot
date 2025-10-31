package com.wlmd.discord_bot.model;

// The model representing server users

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import net.dv8tion.jda.api.entities.Role;
import com.wlmd.discord_bot.model.MemberActivityModel;
import com.wlmd.discord_bot.model.GuildModel;


@Entity
@Table(
	    name = "Members",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"guild_id", "discordUserId"})
	)
public class MemberModel {
	
	// One-to-one relationship with the user's activity record.
	// A MemberActivityModel must be created and linked to the user manually before saving.
	// Cascade ensures that when a user is saved or deleted, the activity record is also persisted or removed.
	// TODO: See if this is the best way to do it.
	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private MemberActivityModel memberActivity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long userId;
	
	//@Column(nullable = false)
	//private Long guildId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guild_id", nullable = false)
	private GuildModel guild;
	
	@Column(nullable = false) 
	private Long discordGuildId;
		
	@Column(nullable = false)
	private Long discordUserId;
	
	@Column(nullable = true)
	private String nickName;
	
	@Column(nullable = true)
	private String serverEffectiveName;
	
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberRoleModel> roles = new ArrayList<>();
	
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

	public MemberModel() {}
	
	

	public MemberModel(MemberActivityModel memberActivity, Long userId, GuildModel guild, Long discordUserId, String nickName,
			String serverEffectiveName, List<MemberRoleModel> roles, String serverTimeJoined, String globalName,
			String userName, String userEffectiveName, String userAvatarId, String userAvatarUrl,
			String userTimeCreated) {

		this.memberActivity = memberActivity;
		this.userId = userId;
		this.guild = guild;
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


	public GuildModel getGuild() {
		return guild;
	}

	public void setGuild(GuildModel guild) {
		this.guild = guild;
		this.discordGuildId = guild.getDiscordGuildId();
	}
	
	

	public Long getDiscordGuildId() {
		return discordGuildId;
	}



	public void setDiscordGuildId(Long discordGuildId) {
		this.discordGuildId = discordGuildId;
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

	public List<MemberRoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<MemberRoleModel> roles) {
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

	public MemberActivityModel getMemberActivity() {
		return memberActivity;
	}

	public void setMemberActivity(MemberActivityModel memberActivity) {
		this.memberActivity = memberActivity;
	}
	
	public void updateRoles(List<Role> discordRoles) {
	    this.roles.clear();
	    this.roles.addAll(
	        discordRoles.stream()
	            .map(r -> new MemberRoleModel(r.getIdLong(), r.getName(), this))
	            .collect(Collectors.toList())
	    );
	}
	
	public void initializeActivity() {
		if(this.memberActivity == null) {
			this.memberActivity = new MemberActivityModel(this);
		}
	}
	
}

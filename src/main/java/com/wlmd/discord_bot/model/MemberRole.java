package com.wlmd.discord_bot.model;

// The model representing a user's roles on the server

import jakarta.persistence.*;

@Entity
@Table(name = "MemberRoles")
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private String roleName; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private MemberModel member;

    public MemberRole() {}

    public MemberRole(Long roleId, String roleName, MemberModel member) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.member = member;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public MemberModel getMember() {
		return member;
	}

	public void setMember(MemberModel member) {
		this.member = member;
	}

    
}

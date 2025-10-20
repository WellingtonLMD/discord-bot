package com.wlmd.discord_bot.dto;

//TODO: Not currently in use, check if there is a need for it.

public class UserRoleDto {

    private Long id;
    private Long roleId;
    private String roleName;
    private Long userId;

    public UserRoleDto() {}

    public UserRoleDto(Long id, Long roleId, String roleName, Long userId) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

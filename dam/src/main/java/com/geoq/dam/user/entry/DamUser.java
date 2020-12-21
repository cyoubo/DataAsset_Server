package com.geoq.dam.user.entry;

public class DamUser {
    private String uuid;

    private String username;

    private String password;

    private String role;

    private String departmentuuid;

    public DamUser(String uuid, String username, String password, String role, String departmentuuid) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.departmentuuid = departmentuuid;
    }

    public DamUser() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDepartmentuuid() {
        return departmentuuid;
    }

    public void setDepartmentuuid(String departmentuuid) {
        this.departmentuuid = departmentuuid == null ? null : departmentuuid.trim();
    }
}
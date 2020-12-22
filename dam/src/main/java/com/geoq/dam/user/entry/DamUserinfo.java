package com.geoq.dam.user.entry;

public class DamUserinfo {
	
	private String uuid;

    private String name;

    private String password;

    private String roleid;

    private String departmentuuid;

    public DamUserinfo(String uuid, String name, String password, String roleid, String departmentuuid) {
        this.uuid = uuid;
        this.name = name;
        this.password = password;
        this.roleid = roleid;
        this.departmentuuid = departmentuuid;
    }

    public DamUserinfo() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getDepartmentuuid() {
        return departmentuuid;
    }

    public void setDepartmentuuid(String departmentuuid) {
        this.departmentuuid = departmentuuid == null ? null : departmentuuid.trim();
    }
}
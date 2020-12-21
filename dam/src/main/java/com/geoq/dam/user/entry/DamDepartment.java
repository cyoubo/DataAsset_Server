package com.geoq.dam.user.entry;

public class DamDepartment {
    private String uuid;

    private String departmentname;

    public DamDepartment(String uuid, String departmentname) {
        this.uuid = uuid;
        this.departmentname = departmentname;
    }

    public DamDepartment() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }
}
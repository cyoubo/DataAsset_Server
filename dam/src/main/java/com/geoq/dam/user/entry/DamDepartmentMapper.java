package com.geoq.dam.user.entry;

public interface DamDepartmentMapper {
    int insert(DamDepartment record);

    int insertSelective(DamDepartment record);
}
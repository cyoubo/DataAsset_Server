package com.geoq.dam.user.entry;

import java.util.List;

public interface DamDepartmentMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DamDepartment record);

    int insertSelective(DamDepartment record);

    DamDepartment selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(DamDepartment record);

    int updateByPrimaryKey(DamDepartment record);

	List<DamDepartment> travelAll();
}
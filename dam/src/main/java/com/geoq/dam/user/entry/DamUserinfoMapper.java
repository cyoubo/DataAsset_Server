package com.geoq.dam.user.entry;

import java.util.List;

import com.geoq.dam.user.bean.UserFilterBean;

public interface DamUserinfoMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(DamUserinfo record);

    int insertSelective(DamUserinfo record);

    DamUserinfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(DamUserinfo record);

    int updateByPrimaryKey(DamUserinfo record);

	List<DamUserinfo> selectByFilter(UserFilterBean bean);
	
	List<DamUserinfo> travelAll();
}
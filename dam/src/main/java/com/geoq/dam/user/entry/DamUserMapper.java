package com.geoq.dam.user.entry;

public interface DamUserMapper {
    int insert(DamUser record);
    int insertSelective(DamUser record);
}
package com.crk.hs.dao;

import com.crk.hs.entity.Regist;

public interface RegistMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Regist record);

    int insertSelective(Regist record);

    Regist selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Regist record);

    int updateByPrimaryKey(Regist record);
}
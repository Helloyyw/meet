package com.crk.hs.dao;

public interface UserMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
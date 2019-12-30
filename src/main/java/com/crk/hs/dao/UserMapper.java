package com.crk.hs.dao;

import com.crk.hs.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
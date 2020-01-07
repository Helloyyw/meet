package com.crk.hs.dao;

import com.crk.hs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
public interface UserMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getByUserNameAndPwd(@Param("username") String username,@Param("password") String password);
}
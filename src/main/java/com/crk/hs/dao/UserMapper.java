package com.crk.hs.dao;

import com.crk.hs.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getByUserNameAndPwd(@Param("username") String username,@Param("password") String password);

    Page<User> getUserList();

    List<User> getUserBynameAndid(@Param("name")String name,@Param("cardid") String cardid);

    List<String> getUserNameByBsm(@Param("uBsm")List<String> uBsm);

    User getUserByemail(String email);
}
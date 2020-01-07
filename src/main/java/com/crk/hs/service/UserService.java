package com.crk.hs.service;

import com.crk.hs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
public interface UserService {
  //根据用户名和密码查询用户信息
   public User getByUserNameAndPwd(String username,String password);
}

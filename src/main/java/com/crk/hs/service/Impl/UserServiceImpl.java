package com.crk.hs.service.Impl;

import com.crk.hs.dao.UserMapper;
import com.crk.hs.entity.User;
import com.crk.hs.service.UserService;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl  {

    @Resource
    UserMapper userMapper;

    /**
     * 验证用户名密码
     * @param username
     * @param password
     * @return
     */
    public User getByUserNameAndPwd(String username, String password) {
        User userInfo  = null;
        try {
            userInfo = userMapper.getByUserNameAndPwd(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return userInfo;
    }


//    @Override
//    public ResultVo getAllUsers() {
//        List<User> allUsers = userDao.getAllUsers();
//        if(allUsers.size()==0){
//            return ResultVo.error(CodeMsg.SELECT_ERROR);
//        }else{
//            return ResultVo.success(allUsers);
//        }
//    }


}

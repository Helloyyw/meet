package com.crk.hs.service.Impl;

import com.crk.hs.service.UserService;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public ResultVo getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        if(allUsers.size()==0){
            return ResultVo.error(CodeMsg.SELECT_ERROR);
        }else{
            return ResultVo.success(allUsers);
        }
    }


}

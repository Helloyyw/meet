package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.UserMapper;
import com.crk.hs.entity.User;
import com.crk.hs.tools.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

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

    /**
     * 得到用户列表
     * @return
     */
    public Page<User> getUserList(String json) {
        //解密json数据
        try {
            json =   URLDecoder.decode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        //分页参数
       int pageNum1 = 1;
        String pageNum = jsonObject.get("pageNum").toString();
        if(!StringUtil.isNullOrEmpty(pageNum)){
            pageNum1 =  Integer.parseInt(pageNum);
        }
        int pageSize1 = 10;
        String pageSize = jsonObject.get("pageSize").toString();
        if(!StringUtil.isNullOrEmpty(pageSize)){
            pageSize1 =  Integer.parseInt(pageSize);
        }
        PageHelper.startPage(pageNum1,pageSize1);
        Page<User> userList = null;
        try {
            userList = userMapper.getUserList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return userList;
    }
    /**
     * 修改用户信息
     * @param form
     * @return
     */
    public int updateuser(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        User userinfo = JSONObject.parseObject(form,User.class);
        int isupdate = 0;
        try {
            isupdate = userMapper.updateByPrimaryKeySelective(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isupdate;
    }

    /**
     * 根据条件查询用户
     * @param form
     * @return
     */
    public List<User> searchUser(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String name = jsonObject.get("name").toString();
        String cardid = jsonObject.get("cardid").toString();
        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<User> userList = null;
        try {
            userList = userMapper.getUserBynameAndid(name,cardid);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return userList;
    }

    public int addUser(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        User userinfo = JSONObject.parseObject(form,User.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        int isAdd = 0;
        try {
            isAdd = userMapper.insert(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }
}

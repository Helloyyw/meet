package com.crk.hs.controller;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.annotation.PermissionCheck;
import com.crk.hs.entity.User;
import com.crk.hs.service.Impl.UserServiceImpl;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserServiceImpl userService;

    /**
     * 查询所有用户列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getUserList", produces = "application/json;charset=UTF-8")
    public ResultVo getUserList(@RequestBody  String pageParm){

        Page<User> userList = null;
        try {
            userList = userService.getUserList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<User> userPage = new PageInfo<>(userList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 修改用户信息
     * @param form
     * @return
     */
    @PostMapping(value = "/updateuser", produces = "application/json;charset=UTF-8")
    public ResultVo updateuser(@RequestBody  String form){

     int userList = 0;
        try {
            userList = userService.updateuser(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userList == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(userList);
        }
    }
    /**
     * 根据条件查询用户
     * @param form
     * @return
     */
    @PostMapping(value = "/searchuser", produces = "application/json;charset=UTF-8")
    public ResultVo searchUser(@RequestBody  String form){

        List<User> userList = null;
        try {
            userList = userService.searchUser(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<User> userPage = new PageInfo<>(userList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 添加用户
     * @param form
     * @return
     */
    @PermissionCheck(role="2") //表示管理员权限
    @PostMapping(value = "/adduser", produces = "application/json;charset=UTF-8")
    public ResultVo addUser(@RequestBody  String form){

        int userList = 0;
        try {
            userList = userService.addUser(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userList == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(userList);
        }
    }
}

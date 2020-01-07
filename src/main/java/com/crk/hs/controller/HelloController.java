package com.crk.hs.controller;

import com.crk.hs.service.UserService;
import com.crk.hs.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
   protected UserService userService;

    @ResponseBody
    @RequestMapping("/test1")
    public  String test(@RequestParam("1") String JSON){
        return "测试返回数据"+JSON;
    }

//
////    访问首页
//    @RequestMapping(value = {"/","/index.html"})
//    public  String test1(){
//        return "index";
//    }
//    @RequestMapping(value ="/user")
//    public  ResultVo userList(){
//        ResultVo allUsers = userService.getAllUsers();
//        return allUsers;
//    }

}

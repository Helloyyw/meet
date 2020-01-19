package com.crk.hs.controller;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.entity.User;
import com.crk.hs.service.Impl.UserServiceImpl;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/index")
public class LoginController {

    @Resource
    UserServiceImpl userService;
    @Resource
     com.crk.hs.mail.MailService MailService;
    /**
     * 系统启动 跳转登录页
     * @return
     */
    @RequestMapping("/login")
    public  String login(){

        return "login";
    }

    /**
     * 登录验证 接收json 返回json
     * @param  json 包含用户名和密码
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/check", produces = "application/json;charset=UTF-8")
    public ResultVo test(@RequestBody  String json){
        //解密json数据
        try {
            json =   URLDecoder.decode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(json);

        String username = jsonObject.get("username").toString();
        String pwd = jsonObject.get("password").toString();
        User userInfo = null;
        try {
             userInfo = userService.getByUserNameAndPwd(username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userInfo == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(userInfo);
        }
    }

    /**
     * 忘记密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgot")
    public ResultVo  forgot(@RequestBody  String json){
//解密json数据
        try {
            json =   URLDecoder.decode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(json);

        String email = jsonObject.get("email").toString();

        User userInfo = null;
        try {
            userInfo = userService.getByEmail(email);
            String to = email;
            String subject = "你的系统密码是：  ";
            String rscId = "img110";
            String content = "<html><body><img width='250px' src=\'cid:" + rscId + "\'> <h1>" +
                     userInfo.getPwd() +
                    "</h1></body></html>";
            // 此处为linux系统路径
            String imgPath = "E:\\bishe\\cqrwkj\\lmz_meet\\meet\\meet\\src\\main\\resources\\static\\dist\\img\\avatar\\avatar-1.jpeg";
            try {
                MailService.sendInlineResourceMail(to, subject, content, imgPath, rscId);
                System.out.println("邮件发送成功了");
            } catch (MessagingException e) {
                System.out.println("邮件发送失败了");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userInfo == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(userInfo);
        }
    }
}

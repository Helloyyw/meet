package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.CarMapper;
import com.crk.hs.dao.JsjMapper;
import com.crk.hs.dao.JsjMapper;
import com.crk.hs.dao.UserMapper;
import com.crk.hs.entity.Car;
import com.crk.hs.entity.Hyxx;
import com.crk.hs.entity.Jsj;
import com.crk.hs.entity.Room;
import com.crk.hs.tools.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class JsjServiceImpl {
    @Resource
    JsjMapper JsjMapper;
    @Resource
    CarMapper carMapper;
    @Resource
    UserMapper userMapper;

    /**
     * 得到车辆列表
     * @return
     */
    public Page<Jsj> getJsjList(String json) {
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
        Page<Jsj> JsjList = null;
        try {
            JsjList = JsjMapper.getJsjList();
            //取出车牌号 查询出对应的信息和乘客信息
            for(Jsj hy : JsjList){
                String ubsm = hy.getuBsm();
                String[] splitubsm = ubsm.split(",");
                List<String> uBsm = Arrays.asList(splitubsm);
                List<String> userNameList = userMapper.getUserNameByBsm(uBsm);
                String userNames = userNameList.toString();
                hy.setuBsm(userNames);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return JsjList;
    }

    /**
     * 根据条件查询车辆信息
     * @param form
     * @return
     */
    public List<Jsj> searchJsj(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String carno = jsonObject.get("carno").toString().trim();
        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<Jsj> JsjsList = null;
        try {
            JsjsList = JsjMapper.getJsjBycarno(carno);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return JsjsList;
    }

    /**
     * 添加车辆
     * @param form
     * @return
     */
    public int addJsj(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsobj = JSONObject.parseObject(form);
        String uBsm = jsobj.getString("bsmStr").toString();
        //转化为json对象
        Jsj userinfo = JSONObject.parseObject(form,Jsj.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        userinfo.setuBsm(uBsm);
        int isAdd = 0;
        try {
            isAdd = JsjMapper.insert(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }

    /**
     * 修改车辆
     * @param form
     * @return
     */
    public int updateJsj(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Jsj userinfo = JSONObject.parseObject(form, Jsj.class);
        JSONObject jsobj = JSONObject.parseObject(form);
        String uBsm = jsobj.getString("bsmStr").toString();
        userinfo.setuBsm(uBsm);
        int isupdate = 0;
        try {
            isupdate = JsjMapper.updateByPrimaryKeySelective(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isupdate;
    }

    public Jsj getJsjByBsm(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String jsjbsm = jsonObject.get("jsjbsm").toString().trim();

        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        Jsj CarsList = null;
        try {
            CarsList = JsjMapper.selectByPrimaryKey(jsjbsm);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarsList;

    }
}

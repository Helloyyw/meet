package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.CarMapper;
import com.crk.hs.entity.Car;
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
public class CarServiceImpl {
    @Resource
    CarMapper carMapper;
    /**
     * 得到车辆列表
     * @return
     */
    public Page<Car> getCarList(String json) {
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
        Page<Car> CarList = null;
        try {
            CarList = carMapper.getCarList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarList;
    }

    /**
     * 根据条件查询车辆信息
     * @param form
     * @return
     */
    public List<Car> searchCar(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String pingpai = jsonObject.get("pingpai").toString().trim();
        int status = 0;
        String status1 = jsonObject.get("status").toString().trim();
        if(!StringUtil.isNullOrEmpty(status1)){
            status = Integer.parseInt(status1);
        }
        String carno = jsonObject.get("carno").toString().trim();
        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<Car> CarsList = null;
        try {
            CarsList = carMapper.getCarBystatusAndpingpaiCp(pingpai,status,carno);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarsList;
    }

    /**
     * 添加车辆
     * @param form
     * @return
     */
    public int addCar(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Car userinfo = JSONObject.parseObject(form,Car.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        int isAdd = 0;
        try {
            isAdd = carMapper.insert(userinfo);
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
    public int updateCar(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Car userinfo = JSONObject.parseObject(form, Car.class);
        int isupdate = 0;
        try {
            isupdate = carMapper.updateByPrimaryKeySelective(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isupdate;
    }
}

package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.RoomMapper;
import com.crk.hs.entity.Room;
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
public class RoomServiceImpl {
    @Resource
    RoomMapper roomMapper;
    /**
     * 得到房间列表
     * @return
     */
    public Page<Room> getRoomList(String json) {
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
        Page<Room> CarList = null;
        try {
            CarList = roomMapper.getRoomList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarList;
    }

    /**
     * 根据条件查询房间信息
     * @param form
     * @return
     */
    public List<Room> searchRoom(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String roomno = jsonObject.get("roomno").toString().trim();
        String roomname = jsonObject.get("roomname").toString().trim();
        int capacity = 0;
        String capacity1 = jsonObject.get("capacity").toString().trim();
        if(!StringUtil.isNullOrEmpty(capacity1)){
            capacity = Integer.parseInt(capacity1);
        }
        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<Room> CarsList = null;
        try {
            CarsList = roomMapper.getRoomBystatusAndpingpaiCp(roomno,roomname,capacity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarsList;
    }

    /**
     * 添加房间信息
     * @param form
     * @return
     */
    public int addRoom(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Room userinfo = JSONObject.parseObject(form,Room.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        int isAdd = 0;
        try {
            isAdd = roomMapper.insert(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }

    /**
     * 修改房间信息
     * @param form
     * @return
     */
    public int updateRoom(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Room userinfo = JSONObject.parseObject(form, Room.class);
        int isupdate = 0;
        try {
            isupdate = roomMapper.updateByPrimaryKeySelective(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isupdate;
    }
}

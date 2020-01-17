package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.HyxxMapper;
import com.crk.hs.dao.RoomMapper;
import com.crk.hs.dao.UserMapper;
import com.crk.hs.entity.Car;
import com.crk.hs.entity.Hyxx;
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
public class HyxxServiceImpl {
    @Resource
    HyxxMapper hyxxMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RoomMapper roomMapper;

    /**
     * 添加会议
     * @param form
     * @return
     */
    public int addHyxx(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsobj = JSONObject.parseObject(form);
        String uBsm = jsobj.getString("bsmStr").toString();
        //转化为json对象
        Hyxx userinfo = JSONObject.parseObject(form, Hyxx.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        userinfo.setuBsm(uBsm);
        int isAdd = 0;
        try {
            isAdd = hyxxMapper.insert(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }

    public Page<Hyxx> getHyList(String pageParm) {
        //解密json数据
        try {
            pageParm =   URLDecoder.decode(pageParm, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(pageParm);
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
        Page<Hyxx> CarList = null;

        try {
            CarList = hyxxMapper.getHyxxList();
            //取出用户标识码 查询出对应的用户信息
            for(Hyxx hy : CarList){
                String ubsm = hy.getuBsm();
                String mroombsm = hy.getMroomBsm();
                Room room = roomMapper.selectByPrimaryKey(mroombsm);
                hy.setMroomBsm(room.getRoomname());
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
        return CarList;
    }

    public List<Hyxx> searchHyxx(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String name = jsonObject.get("name").toString().trim();

        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<Hyxx> CarsList = null;
        try {
            CarsList = hyxxMapper.getHyByname(name);
            //取出用户标识码 查询出对应的用户信息
            for(Hyxx hy : CarsList){
                String ubsm = hy.getuBsm();
                String mroombsm = hy.getMroomBsm();
                Room room = roomMapper.selectByPrimaryKey(mroombsm);
                hy.setMroomBsm(room.getRoomname());
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
        return CarsList;
    }

    public Hyxx getHyxxBybsm(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String hybsm = jsonObject.get("hybsm").toString().trim();

        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
       Hyxx CarsList = null;
        try {
            CarsList = hyxxMapper.selectByPrimaryKey(hybsm);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return CarsList;
    }

    public int updateHyxx(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsobj = JSONObject.parseObject(form);
        String uBsm = jsobj.getString("bsmStr").toString();
        //转化为json对象
        Hyxx userinfo = JSONObject.parseObject(form, Hyxx.class);
        userinfo.setuBsm(uBsm);
        int isAdd = 0;
        try {
            isAdd = hyxxMapper.updateByPrimaryKey(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }
}

package com.crk.hs.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.dao.GiftMapper;
import com.crk.hs.dao.UserMapper;
import com.crk.hs.entity.Gift;
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
public class GiftServiceImpl {
    @Resource
    GiftMapper giftMapper;
    /**
     * 得到礼品列表
     * @return
     */
    public Page<Gift> getGiftList(String json) {
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
        Page<Gift> giftList = null;
        try {
            giftList = giftMapper.getGiftList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return giftList;
    }

    public List<Gift> searchgift(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(form);
        String name = jsonObject.get("name").toString();
        String type = jsonObject.get("type").toString();
        //分页参数
        int pageNum1 = 1;
        int pageSize1 = 10;
        PageHelper.startPage(pageNum1,pageSize1);
        List<Gift> giftsList = null;
        try {
            giftsList = giftMapper.getGiftBynameAndtype(name,type);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return giftsList;
    }

    public int addgift(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Gift userinfo = JSONObject.parseObject(form,Gift.class);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userinfo.setBsm(uuid);
        int isAdd = 0;
        try {
            isAdd = giftMapper.insert(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isAdd;
    }

    public int updategift(String form) {
        //解密json数据
        try {
            form =   URLDecoder.decode(form, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //转化为json对象
        Gift userinfo = JSONObject.parseObject(form,Gift.class);
        int isupdate = 0;
        try {
            isupdate = giftMapper.updateByPrimaryKeySelective(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return isupdate;
    }
}

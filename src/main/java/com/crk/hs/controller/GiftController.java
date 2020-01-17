package com.crk.hs.controller;

import com.crk.hs.entity.Gift;
import com.crk.hs.entity.User;
import com.crk.hs.service.Impl.GiftServiceImpl;
import com.crk.hs.service.Impl.UserServiceImpl;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/gift")
public class GiftController {
    @Resource
    GiftServiceImpl giftService;

    /**
     * 查询所有礼品列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getGiftList", produces = "application/json;charset=UTF-8")
    public ResultVo getGiftList(@RequestBody String pageParm){

        Page<Gift> giftList = null;
        try {
            giftList = giftService.getGiftList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(giftList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Gift> userPage = new PageInfo<>(giftList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 根据礼品名称和类型查询礼品
     * @param form
     * @return
     */
    @PostMapping(value = "/searchgift", produces = "application/json;charset=UTF-8")
    public ResultVo searchgift(@RequestBody  String form){

        List<Gift> giftsList = null;
        try {
            giftsList = giftService.searchgift(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(giftsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Gift> userPage = new PageInfo<>(giftsList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 添加礼品实体
     * @param form
     * @return
     */
    @PostMapping(value = "/addgift", produces = "application/json;charset=UTF-8")
    public ResultVo addgift(@RequestBody  String form){

        int gift = 0;
        try {
            gift = giftService.addgift(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(gift == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(gift);
        }
    }
    /**
     * 修改礼品信息
     * @param form
     * @return
     */
    @PostMapping(value = "/updategift", produces = "application/json;charset=UTF-8")
    public ResultVo updategift(@RequestBody  String form){

        int userList = 0;
        try {
            userList = giftService.updategift(form);
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

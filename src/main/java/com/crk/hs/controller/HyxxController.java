package com.crk.hs.controller;

import com.crk.hs.entity.Car;
import com.crk.hs.entity.Gift;
import com.crk.hs.entity.Hyxx;
import com.crk.hs.service.Impl.HyxxServiceImpl;
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
@RequestMapping("/hyxx")
public class HyxxController {

    @Resource
    HyxxServiceImpl hyxxService;


    /**
     * 查询所有会议列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getHyList", produces = "application/json;charset=UTF-8")
    public ResultVo getHyList(@RequestBody String pageParm){

        Page<Hyxx> giftList = null;
        try {
            giftList = hyxxService.getHyList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(giftList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Hyxx> userPage = new PageInfo<>(giftList);
            return ResultVo.success(userPage);
        }
    }
    /**
     * 添加会议实体
     * @param form
     * @return
     */
    @PostMapping(value = "/addHyxx", produces = "application/json;charset=UTF-8")
    public ResultVo addHyxx(@RequestBody String form){

        int Hyxx = 0;
        try {
            Hyxx = hyxxService.addHyxx(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Hyxx == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(Hyxx);
        }
    }
  /**
     * 更新会议实体
     * @param form
     * @return
     */
    @PostMapping(value = "/updateHyxx", produces = "application/json;charset=UTF-8")
    public ResultVo updateHyxx(@RequestBody String form){

        int Hyxx = 0;
        try {
            Hyxx = hyxxService.updateHyxx(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Hyxx == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(Hyxx);
        }
    }

    /**
     * 根据会议名称查询
     * @param form
     * @return
     */
    @PostMapping(value = "/searchHyxx", produces = "application/json;charset=UTF-8")
    public ResultVo searchHyxx(@RequestBody  String form){

        List<Hyxx> CarsList = null;
        try {
            CarsList = hyxxService.searchHyxx(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Hyxx> userPage = new PageInfo<>(CarsList);
            return ResultVo.success(userPage);
        }
    }
     /**
     * 根据会议bsm查询
     * @param form
     * @return
     */
    @PostMapping(value = "/getHyxxBybsm", produces = "application/json;charset=UTF-8")
    public ResultVo getHyxxBybsm(@RequestBody  String form){

        Hyxx CarsList = null;
        try {
            CarsList = hyxxService.getHyxxBybsm(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(CarsList);
        }
    }

}

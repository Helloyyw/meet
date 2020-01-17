package com.crk.hs.controller;

import com.crk.hs.entity.Car;
import com.crk.hs.entity.Jsj;
import com.crk.hs.service.Impl.CarServiceImpl;
import com.crk.hs.service.Impl.JsjServiceImpl;
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
@RequestMapping("/jsj")
public class JsjController {
    @Resource
    JsjServiceImpl jsjService;

    /**
     * 查询所有车辆列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getJsjList", produces = "application/json;charset=UTF-8")
    public ResultVo getJsjList(@RequestBody String pageParm){

        Page<Jsj> JsjList = null;
        try {
            JsjList = jsjService.getJsjList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(JsjList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Jsj> userPage = new PageInfo<>(JsjList);
            return ResultVo.success(userPage);
        }
    }
    /**
     * 查询所有车辆列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getHyxxBybsm", produces = "application/json;charset=UTF-8")
    public ResultVo getJsjByBsm(@RequestBody String pageParm){

        Jsj JsjList = null;
        try {
            JsjList = jsjService.getJsjByBsm(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(JsjList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
           // PageInfo<Jsj userPage = new PageInfo<>(JsjList);
            return ResultVo.success(JsjList);
        }
    }

    /**
     * 根据车辆品牌和状态和容量查询
     * @param form
     * @return
     */
    @PostMapping(value = "/searchJsj", produces = "application/json;charset=UTF-8")
    public ResultVo searchJsj(@RequestBody  String form){

        List<Jsj> JsjsList = null;
        try {
            JsjsList = jsjService.searchJsj(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(JsjsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Jsj> userPage = new PageInfo<>(JsjsList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 添加车辆实体
     * @param form
     * @return
     */
    @PostMapping(value = "/addJsjxx", produces = "application/json;charset=UTF-8")
    public ResultVo addJsjxx(@RequestBody  String form){

        int Jsj = 0;
        try {
            Jsj = jsjService.addJsj(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Jsj == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(Jsj);
        }
    }
    /**
     * 修改车辆信息
     * @param form
     * @return
     */
    @PostMapping(value = "/updateJsj", produces = "application/json;charset=UTF-8")
    public ResultVo updateJsj(@RequestBody  String form){

        int userList = 0;
        try {
            userList = jsjService.updateJsj(form);
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

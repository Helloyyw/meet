package com.crk.hs.controller;

import com.crk.hs.entity.Car;
import com.crk.hs.service.Impl.CarServiceImpl;
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
@RequestMapping("/car")
public class CarController {
    @Resource
    CarServiceImpl carService;

    /**
     * 查询所有车辆列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getCarList", produces = "application/json;charset=UTF-8")
    public ResultVo getCarList(@RequestBody String pageParm){

        Page<Car> CarList = null;
        try {
            CarList = carService.getCarList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Car> userPage = new PageInfo<>(CarList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 根据车辆品牌和状态和容量查询
     * @param form
     * @return
     */
    @PostMapping(value = "/searchcar", produces = "application/json;charset=UTF-8")
    public ResultVo searchCar(@RequestBody  String form){

        List<Car> CarsList = null;
        try {
            CarsList = carService.searchCar(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Car> userPage = new PageInfo<>(CarsList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 添加车辆实体
     * @param form
     * @return
     */
    @PostMapping(value = "/addcar", produces = "application/json;charset=UTF-8")
    public ResultVo addCar(@RequestBody  String form){

        int Car = 0;
        try {
            Car = carService.addCar(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Car == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(Car);
        }
    }
    /**
     * 修改车辆信息
     * @param form
     * @return
     */
    @PostMapping(value = "/updatecar", produces = "application/json;charset=UTF-8")
    public ResultVo updateCar(@RequestBody  String form){

        int userList = 0;
        try {
            userList = carService.updateCar(form);
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

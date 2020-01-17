package com.crk.hs.controller;

import com.crk.hs.entity.Room;
import com.crk.hs.service.Impl.RoomServiceImpl;
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
@RequestMapping("/room")
public class RoomController {
    @Resource
    RoomServiceImpl roomService;

    /**
     * 查询所有房间列表
     * @param pageParm
     * @return
     */
    @PostMapping(value = "/getRoomList", produces = "application/json;charset=UTF-8")
    public ResultVo getRoomList(@RequestBody String pageParm){

        Page<Room> CarList = null;
        try {
            CarList = roomService.getRoomList(pageParm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarList == null){

            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Room> userPage = new PageInfo<>(CarList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 根据房间号和房间名和容量查询
     * @param form
     * @return
     */
    @PostMapping(value = "/searchRoom", produces = "application/json;charset=UTF-8")
    public ResultVo searchRoom(@RequestBody  String form){

        List<Room> CarsList = null;
        try {
            CarsList = roomService.searchRoom(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(CarsList == null){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            PageInfo<Room> userPage = new PageInfo<>(CarsList);
            return ResultVo.success(userPage);
        }
    }

    /**
     * 添加车房间实体
     * @param form
     * @return
     */
    @PostMapping(value = "/addRoom", produces = "application/json;charset=UTF-8")
    public ResultVo addRoom(@RequestBody  String form){

        int room = 0;
        try {
            room = roomService.addRoom(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(room == 0){
            return  ResultVo.error(CodeMsg.SELECT_ERROR);
        }else {
            return ResultVo.success(room);
        }
    }
    /**
     * 修改房间信息
     * @param form
     * @return
     */
    @PostMapping(value = "/updateRoom", produces = "application/json;charset=UTF-8")
    public ResultVo updateRoom(@RequestBody  String form){

        int userList = 0;
        try {
            userList = roomService.updateRoom(form);
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

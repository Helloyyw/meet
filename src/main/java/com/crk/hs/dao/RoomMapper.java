package com.crk.hs.dao;

import com.crk.hs.entity.Hyxx;
import com.crk.hs.entity.Room;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    Page<Room> getRoomList();

    List<Room> getRoomBystatusAndpingpaiCp(@Param("roomno") String roomno, @Param("roomname") String roomname, @Param("capacity")  int capacity);


}
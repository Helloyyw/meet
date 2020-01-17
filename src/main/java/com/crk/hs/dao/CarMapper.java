package com.crk.hs.dao;

import com.crk.hs.entity.Car;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CarMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
    //分页查找车辆列表
    Page<Car> getCarList();

    List<Car> getCarBystatusAndpingpaiCp(@Param("pingpai") String pingpai,@Param("status") int status, @Param("carno")String carno);
}
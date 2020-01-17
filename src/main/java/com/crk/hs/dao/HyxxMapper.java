package com.crk.hs.dao;

import com.crk.hs.entity.Hyxx;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HyxxMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Hyxx record);

    int insertSelective(Hyxx record);

    Hyxx selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Hyxx record);

    int updateByPrimaryKey(Hyxx record);

    Page<Hyxx> getHyxxList();

    List<Hyxx> getHyByname(@Param("name") String name);
}
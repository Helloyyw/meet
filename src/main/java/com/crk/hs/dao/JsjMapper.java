package com.crk.hs.dao;

import com.crk.hs.entity.Jsj;
import com.github.pagehelper.Page;

import java.util.List;

public interface JsjMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Jsj record);

    int insertSelective(Jsj record);

    Jsj selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Jsj record);

    int updateByPrimaryKey(Jsj record);

    Page<Jsj> getJsjList();
    List<Jsj> getJsjBycarno(String carno);
}
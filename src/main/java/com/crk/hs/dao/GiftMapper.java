package com.crk.hs.dao;

import com.crk.hs.entity.Gift;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GiftMapper {
    int deleteByPrimaryKey(String bsm);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(String bsm);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);
        //分页查找礼品列表
    Page<Gift> getGiftList();

    List<Gift> getGiftBynameAndtype(@Param("name") String name, @Param("type") String type);
}
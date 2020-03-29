package com.zhxx.service.szxt.mapper;

import com.zhxx.service.szxt.entity.DimensionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 维度的增删改查 mapper层
 *
 * @author:高明凯 Created by dell on 2018/12/11 14:30
 */
@Repository
@Mapper
public interface DimensionEntityMapper {
    /*
    添加方法
     */
    int insertDimension(DimensionEntity dimensionEntity);

    /*
   删除方法
    */
    int deleteDimension(String dimensionId);

    /*
   修改方法
    */
    int updateDimension(DimensionEntity record);

    /*
   查询方法
    */
    List<DimensionEntity> selectDimension(DimensionEntity record);

    /*
    查询所有
     */
    List<DimensionEntity> selectAllDimension();

    DimensionEntity selectByPrimaryKey(String dimensionId);
}
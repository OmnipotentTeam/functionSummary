package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.AccessModelOptionCommonEntity;
@Mapper
public interface AssessFunMapper {
    List<AccessModelOptionCommonEntity> queryModelList(AccessModelOptionCommonEntity accessModelOptionCommonEntity);
    List<AccessModelOptionCommonEntity> queryModelAndOptionList(AccessModelOptionCommonEntity accessModelOptionCommonEntity);
}

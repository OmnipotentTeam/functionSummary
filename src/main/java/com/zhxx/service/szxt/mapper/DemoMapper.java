package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.Demosel;

@Mapper
public interface DemoMapper {
    public List<Demosel> queryAll();

    public Integer querycount();
}

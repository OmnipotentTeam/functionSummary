package com.zhxx.service.szxt.mapper;

import com.zhxx.service.szxt.entity.MajorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MajorEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table major
     *
     * @mbg.generated
     */
    int insert(MajorEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table major
     *
     * @mbg.generated
     */
    int insertSelective(MajorEntity record);

    List<MajorEntity> selectMajorAll(MajorEntity majorEntity);

    int updateMajor(MajorEntity majorEntity);

    List<MajorEntity> selectMajor();
}
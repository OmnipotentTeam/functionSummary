package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherPatentEntity;
import com.zhxx.service.szxt.entity.TeacherPrizeEntity;
import com.zhxx.service.szxt.entity.TeacherUserEntity;
@Mapper
public interface TeacherPatentEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_patent
     *
     * @mbg.generated
     */
    int insert(TeacherPatentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_patent
     *
     * @mbg.generated
     */
    int insertSelective(TeacherPatentEntity record);
    
    int updateByPrimaryKeySelective(TeacherPatentEntity record);
    
    int deleteByPrimaryKey(String id);
    
    List<TeacherPatentEntity> selectList(TeacherPatentEntity record);
    List<TeacherPatentEntity> queryForExcel(TeacherUserEntity record);
}
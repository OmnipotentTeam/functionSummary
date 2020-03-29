package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherPrizeEntity;
import com.zhxx.service.szxt.entity.TeacherUserEntity;
@Mapper
public interface TeacherPrizeEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    int insert(TeacherPrizeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    int insertSelective(TeacherPrizeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    TeacherPrizeEntity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TeacherPrizeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_prize
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TeacherPrizeEntity record);
    
    List<TeacherPrizeEntity> selectList(TeacherPrizeEntity record);
    List<TeacherPrizeEntity> queryForExcel(TeacherUserEntity record);
}
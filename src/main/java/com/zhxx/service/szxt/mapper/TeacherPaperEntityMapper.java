package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherUserEntity;
@Mapper
public interface TeacherPaperEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    int insert(TeacherPaperEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    int insertSelective(TeacherPaperEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    TeacherPaperEntity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TeacherPaperEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_paper
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TeacherPaperEntity record);

    TeacherPaperEntity selectJou(TeacherPaperEntity record);
    List<TeacherPaperEntity> selectList(TeacherPaperEntity record);
    List<TeacherPaperEntity> queryForExcel(TeacherUserEntity record);
}
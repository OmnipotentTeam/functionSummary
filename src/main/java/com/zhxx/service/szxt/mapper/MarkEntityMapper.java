package com.zhxx.service.szxt.mapper;

import com.zhxx.service.szxt.entity.GradeEvaluationEntity;
import com.zhxx.service.szxt.entity.MarkEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MarkEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String markId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */
    int insert(MarkEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */

    int insertSelective(MarkEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */
    MarkEntity selectByPrimaryKey(String markId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MarkEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mark
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MarkEntity record);

    int updateByPrimaryKey1(MarkEntity record);

    List<GradeEvaluationEntity> selectMarkComplete(String record);

    List<MarkEntity> searchNotMark(Map record);

    List<MarkEntity> selectNotMarkComment(Map record);

    List<MarkEntity> searchMark(Map record);

    List<MarkEntity> searchMarkGrade(Map record);
    List<MarkEntity> searchMarkGrade1(Map record);

    List<MarkEntity> selectMarkComment(Map record);

    List<MarkEntity> markStudentSeestudentId(MarkEntity markEntity);

    int insertAnswerMark(MarkEntity markEntity);

    List<MarkEntity> selectStudentMark(String studentId);

    int pushMark(MarkEntity markEntity);

    List<MarkEntity> selectName(String markId);
}
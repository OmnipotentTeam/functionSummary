package com.zhxx.service.szxt.mapper;

import com.zhxx.service.szxt.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String studentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    int insert(StudentEntity record);
    int insertBindCode(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    int insertSelective(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    StudentEntity selectByPrimaryKey(String studentId);
    List<StudentEntity> selectByStudent(StudentEntity record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StudentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StudentEntity record);

    int addStudent(StudentEntity record);

    StudentEntity selectStudent(String studentId);

    int updateStudentInformation(StudentEntity record);

    List<StudentEntity> selectStudentAll(StudentEntity record);

    int updateStudentGrade();

    int updateStudentRetardation();

    //删除使用
    List<StudentEntity> selectAllId(String id);
    int deleteStudent(StudentEntity studentEntity);

    int insertupdateStudentBindingCode(StudentEntity studentEntity);
    int updateStudentBindingCode(StudentEntity studentEntity);
}
package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.TeacherResearchSectionEntity;
@Mapper
public interface TeacherResearchSectionEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);
    int deleteByparentId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    int insert(TeacherResearchSectionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    int insertSelective(TeacherResearchSectionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    TeacherResearchSectionEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TeacherResearchSectionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher_research_section
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TeacherResearchSectionEntity record);
    
    List<TeacherResearchSectionEntity> listAll();
    List<TeacherResearchSectionEntity> listParentAll();
    
    List<TeacherResearchSectionEntity> queryAll(TeacherResearchSectionEntity record);
}
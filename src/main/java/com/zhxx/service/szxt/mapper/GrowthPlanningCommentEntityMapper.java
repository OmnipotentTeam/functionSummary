package com.zhxx.service.szxt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.GrowthPlanningCommentEntity;
@Mapper
public interface GrowthPlanningCommentEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    int insert(GrowthPlanningCommentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    int insertSelective(GrowthPlanningCommentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    GrowthPlanningCommentEntity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GrowthPlanningCommentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table growth_planning_comment
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GrowthPlanningCommentEntity record);
}
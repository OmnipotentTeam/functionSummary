package com.zhxx.service.szxt.entity;

import java.util.Date;
import java.util.List;

/**
 * 维度的增删改查实体层
 *
 * @author:高明凯 Created by dell on 2018/12/6 14:30
 */
public class DimensionRecordEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.dimension_record_id
     *
     * @mbg.generated
     */
    private String dimensionRecordId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.dimension_id
     *
     * @mbg.generated
     */
    private String dimensionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.dimension_record_num
     *
     * @mbg.generated
     */
    private Integer dimensionRecordNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.mark_id
     *
     * @mbg.generated
     */
    private String markId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension_record.status
     *
     * @mbg.generated
     */
    private Integer status;

    private String dimensionName;

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.dimension_record_id
     *
     * @return the value of dimension_record.dimension_record_id
     * @mbg.generated
     */
    public String getDimensionRecordId() {
        return dimensionRecordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.dimension_record_id
     *
     * @param dimensionRecordId the value for dimension_record.dimension_record_id
     * @mbg.generated
     */
    public void setDimensionRecordId(String dimensionRecordId) {
        this.dimensionRecordId = dimensionRecordId == null ? null : dimensionRecordId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.dimension_id
     *
     * @return the value of dimension_record.dimension_id
     * @mbg.generated
     */
    public String getDimensionId() {
        return dimensionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.dimension_id
     *
     * @param dimensionId the value for dimension_record.dimension_id
     * @mbg.generated
     */
    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId == null ? null : dimensionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.dimension_record_num
     *
     * @return the value of dimension_record.dimension_record_num
     * @mbg.generated
     */
    public Integer getDimensionRecordNum() {
        return dimensionRecordNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.dimension_record_num
     *
     * @param dimensionRecordNum the value for dimension_record.dimension_record_num
     * @mbg.generated
     */
    public void setDimensionRecordNum(Integer dimensionRecordNum) {
        this.dimensionRecordNum = dimensionRecordNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.mark_id
     *
     * @return the value of dimension_record.mark_id
     * @mbg.generated
     */
    public String getMarkId() {
        return markId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.mark_id
     *
     * @param markId the value for dimension_record.mark_id
     * @mbg.generated
     */
    public void setMarkId(String markId) {
        this.markId = markId == null ? null : markId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.create_time
     *
     * @return the value of dimension_record.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.create_time
     *
     * @param createTime the value for dimension_record.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.modify_time
     *
     * @return the value of dimension_record.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.modify_time
     *
     * @param modifyTime the value for dimension_record.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension_record.status
     *
     * @return the value of dimension_record.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension_record.status
     *
     * @param status the value for dimension_record.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    private List<DimensionEntity> dimensionEntity;

    public List<DimensionEntity> getDimensionEntity() {
        return dimensionEntity;
    }

    public void setDimensionEntity(List<DimensionEntity> dimensionEntity) {
        this.dimensionEntity = dimensionEntity;
    }
}
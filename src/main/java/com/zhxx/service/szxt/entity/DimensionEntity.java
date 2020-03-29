package com.zhxx.service.szxt.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DimensionEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension.dimension_id
     *
     * @mbg.generated
     */
    private String dimensionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension.dimension_name
     *
     * @mbg.generated
     */
    private String dimensionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dimension.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension.dimension_id
     *
     * @return the value of dimension.dimension_id
     * @mbg.generated
     */
    public String getDimensionId() {
        return dimensionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension.dimension_id
     *
     * @param dimensionId the value for dimension.dimension_id
     * @mbg.generated
     */
    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId == null ? null : dimensionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension.dimension_name
     *
     * @return the value of dimension.dimension_name
     * @mbg.generated
     */
    public String getDimensionName() {
        return dimensionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension.dimension_name
     *
     * @param dimensionName the value for dimension.dimension_name
     * @mbg.generated
     */
    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName == null ? null : dimensionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension.modify_time
     *
     * @return the value of dimension.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension.modify_time
     *
     * @param modifyTime the value for dimension.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension.create_time
     *
     * @return the value of dimension.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension.create_time
     *
     * @param createTime the value for dimension.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dimension.status
     *
     * @return the value of dimension.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dimension.status
     *
     * @param status the value for dimension.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
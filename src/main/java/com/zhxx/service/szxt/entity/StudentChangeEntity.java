package com.zhxx.service.szxt.entity;

import java.util.Date;

public class StudentChangeEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.student_change_id
     *
     * @mbg.generated
     */
    private String studentChangeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.student_id
     *
     * @mbg.generated
     */
    private String studentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.student_change_describe
     *
     * @mbg.generated
     */
    private String studentChangeDescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_change.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.student_change_id
     *
     * @return the value of student_change.student_change_id
     * @mbg.generated
     */
    public String getStudentChangeId() {
        return studentChangeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.student_change_id
     *
     * @param studentChangeId the value for student_change.student_change_id
     * @mbg.generated
     */
    public void setStudentChangeId(String studentChangeId) {
        this.studentChangeId = studentChangeId == null ? null : studentChangeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.student_id
     *
     * @return the value of student_change.student_id
     * @mbg.generated
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.student_id
     *
     * @param studentId the value for student_change.student_id
     * @mbg.generated
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.user_id
     *
     * @return the value of student_change.user_id
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.user_id
     *
     * @param userId the value for student_change.user_id
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.student_change_describe
     *
     * @return the value of student_change.student_change_describe
     * @mbg.generated
     */
    public String getStudentChangeDescribe() {
        return studentChangeDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.student_change_describe
     *
     * @param studentChangeDescribe the value for student_change.student_change_describe
     * @mbg.generated
     */
    public void setStudentChangeDescribe(String studentChangeDescribe) {
        this.studentChangeDescribe = studentChangeDescribe == null ? null : studentChangeDescribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.create_time
     *
     * @return the value of student_change.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.create_time
     *
     * @param createTime the value for student_change.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.modify_time
     *
     * @return the value of student_change.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.modify_time
     *
     * @param modifyTime the value for student_change.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_change.status
     *
     * @return the value of student_change.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_change.status
     *
     * @param status the value for student_change.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
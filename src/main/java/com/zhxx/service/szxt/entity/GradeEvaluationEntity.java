package com.zhxx.service.szxt.entity;

import java.util.Date;
import java.util.List;

public class GradeEvaluationEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.grade_evaluation_id
     *
     * @mbg.generated
     */
    private String gradeEvaluationId;

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    private String dimensionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.question_id
     *
     * @mbg.generated
     */
    private String questionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.grade
     *
     * @mbg.generated
     */
    private Integer grade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.evaluation_name
     *
     * @mbg.generated
     */
    private String evaluationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.evaluation_date
     *
     * @mbg.generated
     */
    private Date evaluationDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.evaluation_type
     *
     * @mbg.generated
     */
    private Integer evaluationType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.evaluation_status
     *
     * @mbg.generated
     */
    private Integer evaluationStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_evaluation.status
     *
     * @mbg.generated
     */
    private Integer status;

    private Integer pageNum;

    private Integer pageSize;

    private String state;


    private String questionBankName;

    private String username;

    private List<QuestionBankEntity> questionBankEntity;


    private List<SysUserEntity> sysUserEntity;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuestionBankName() {
        return questionBankName;
    }

    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<QuestionBankEntity> getQuestionBankEntity() {
        return questionBankEntity;
    }

    public void setQuestionBankEntity(List<QuestionBankEntity> questionBankEntity) {
        this.questionBankEntity = questionBankEntity;
    }

    public List<SysUserEntity> getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(List<SysUserEntity> sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.grade_evaluation_id
     *
     * @return the value of grade_evaluation.grade_evaluation_id
     * @mbg.generated
     */
    public String getGradeEvaluationId() {
        return gradeEvaluationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.grade_evaluation_id
     *
     * @param gradeEvaluationId the value for grade_evaluation.grade_evaluation_id
     * @mbg.generated
     */
    public void setGradeEvaluationId(String gradeEvaluationId) {
        this.gradeEvaluationId = gradeEvaluationId == null ? null : gradeEvaluationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.question_id
     *
     * @return the value of grade_evaluation.question_id
     * @mbg.generated
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.question_id
     *
     * @param questionId the value for grade_evaluation.question_id
     * @mbg.generated
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.user_id
     *
     * @return the value of grade_evaluation.user_id
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.user_id
     *
     * @param userId the value for grade_evaluation.user_id
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.grade
     *
     * @return the value of grade_evaluation.grade
     * @mbg.generated
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.grade
     *
     * @param grade the value for grade_evaluation.grade
     * @mbg.generated
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.evaluation_name
     *
     * @return the value of grade_evaluation.evaluation_name
     * @mbg.generated
     */
    public String getEvaluationName() {
        return evaluationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.evaluation_name
     *
     * @param evaluationName the value for grade_evaluation.evaluation_name
     * @mbg.generated
     */
    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName == null ? null : evaluationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.evaluation_date
     *
     * @return the value of grade_evaluation.evaluation_date
     * @mbg.generated
     */
    public Date getEvaluationDate() {
        return evaluationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.evaluation_date
     *
     * @param evaluationDate the value for grade_evaluation.evaluation_date
     * @mbg.generated
     */
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.evaluation_type
     *
     * @return the value of grade_evaluation.evaluation_type
     * @mbg.generated
     */
    public Integer getEvaluationType() {
        return evaluationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.evaluation_type
     *
     * @param evaluationType the value for grade_evaluation.evaluation_type
     * @mbg.generated
     */
    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.evaluation_status
     *
     * @return the value of grade_evaluation.evaluation_status
     * @mbg.generated
     */
    public Integer getEvaluationStatus() {
        return evaluationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.evaluation_status
     *
     * @param evaluationStatus the value for grade_evaluation.evaluation_status
     * @mbg.generated
     */
    public void setEvaluationStatus(Integer evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.create_time
     *
     * @return the value of grade_evaluation.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.create_time
     *
     * @param createTime the value for grade_evaluation.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.modify_time
     *
     * @return the value of grade_evaluation.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.modify_time
     *
     * @param modifyTime the value for grade_evaluation.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_evaluation.status
     *
     * @return the value of grade_evaluation.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_evaluation.status
     *
     * @param status the value for grade_evaluation.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


//
//    private List<QuestionBankEntity> questionBankEntities;
//    public List<QuestionBankEntity> getQuestionBankEntity() {
//        return questionBankEntities;
//    }
//    public void setQuestionBankEntity(List<QuestionBankEntity> questionBankEntities) {
//        this.questionBankEntities = questionBankEntities;
//    }

    private List<ProblemEntity> problemEntity;

    public List<ProblemEntity> getProblemEntity() {
        return problemEntity;
    }

    public void setProblemEntity(List<ProblemEntity> problemEntity) {
        this.problemEntity = problemEntity;
    }

}
package com.zhxx.service.szxt.entity;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class QuestionBankEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.question_bank_id
     *
     * @mbg.generated
     */
    private String questionBankId;

    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.question_bank_name
     *
     * @mbg.generated
     */
    private String questionBankName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.question_bank_stage
     *
     * @mbg.generated
     */
    private Integer questionBankStage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.question_bank_introduce
     *
     * @mbg.generated
     */
    private String questionBankIntroduce;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.question_bank_file_path
     *
     * @mbg.generated
     */
    private String questionBankFilePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question_bank.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.question_bank_id
     *
     * @return the value of question_bank.question_bank_id
     * @mbg.generated
     */
    public String getQuestionBankId() {
        return questionBankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.question_bank_id
     *
     * @param questionBankId the value for question_bank.question_bank_id
     * @mbg.generated
     */
    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId == null ? null : questionBankId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.user_id
     *
     * @return the value of question_bank.user_id
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.user_id
     *
     * @param userId the value for question_bank.user_id
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.question_bank_name
     *
     * @return the value of question_bank.question_bank_name
     * @mbg.generated
     */
    public String getQuestionBankName() {
        return questionBankName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.question_bank_name
     *
     * @param questionBankName the value for question_bank.question_bank_name
     * @mbg.generated
     */
    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName == null ? null : questionBankName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.question_bank_stage
     *
     * @return the value of question_bank.question_bank_stage
     * @mbg.generated
     */
    public Integer getQuestionBankStage() {
        return questionBankStage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.question_bank_stage
     *
     * @param questionBankStage the value for question_bank.question_bank_stage
     * @mbg.generated
     */
    public void setQuestionBankStage(Integer questionBankStage) {
        this.questionBankStage = questionBankStage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.question_bank_introduce
     *
     * @return the value of question_bank.question_bank_introduce
     * @mbg.generated
     */
    public String getQuestionBankIntroduce() {
        return questionBankIntroduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.question_bank_introduce
     *
     * @param questionBankIntroduce the value for question_bank.question_bank_introduce
     * @mbg.generated
     */
    public void setQuestionBankIntroduce(String questionBankIntroduce) {
        this.questionBankIntroduce = questionBankIntroduce == null ? null : questionBankIntroduce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.question_bank_file_path
     *
     * @return the value of question_bank.question_bank_file_path
     * @mbg.generated
     */
    public String getQuestionBankFilePath() {
        return questionBankFilePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.question_bank_file_path
     *
     * @param questionBankFilePath the value for question_bank.question_bank_file_path
     * @mbg.generated
     */
    public void setQuestionBankFilePath(String questionBankFilePath) {
        this.questionBankFilePath = questionBankFilePath == null ? null : questionBankFilePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.create_time
     *
     * @return the value of question_bank.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.create_time
     *
     * @param createTime the value for question_bank.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.modify_time
     *
     * @return the value of question_bank.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.modify_time
     *
     * @param modifyTime the value for question_bank.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question_bank.status
     *
     * @return the value of question_bank.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question_bank.status
     *
     * @param status the value for question_bank.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    private List<SolutionEntity> solutionEntity;

    public List<SolutionEntity> getSolutionEntity() {
        return solutionEntity;
    }

    public void setSolutionEntity(List<SolutionEntity> solutionEntity) {
        this.solutionEntity = solutionEntity;
    }
}
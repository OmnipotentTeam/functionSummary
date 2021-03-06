package com.zhxx.service.szxt.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TeacherHorizontalEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.project_no
     *
     * @mbg.generated
     */
    private String projectNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.project_name
     *
     * @mbg.generated
     */
    private String projectName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.host
     *
     * @mbg.generated
     */
    private String host;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.participant
     *
     * @mbg.generated
     */
    private String participant;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.approval_unit
     *
     * @mbg.generated
     */
    private String approvalUnit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.cooperation_unit
     *
     * @mbg.generated
     */
    private String cooperationUnit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.project_time
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date projectTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.amount
     *
     * @mbg.generated
     */
    private String amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.remarks
     *
     * @mbg.generated
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.concluding_time
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date concludingTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.actually_task
     *
     * @mbg.generated
     */
    private String actuallyTask;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.operator
     *
     * @mbg.generated
     */
    private String operator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.create_time
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_horizontal.modify_time
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;
    private String[] picId;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.id
     *
     * @return the value of teacher_horizontal.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.id
     *
     * @param id the value for teacher_horizontal.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.project_no
     *
     * @return the value of teacher_horizontal.project_no
     *
     * @mbg.generated
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.project_no
     *
     * @param projectNo the value for teacher_horizontal.project_no
     *
     * @mbg.generated
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.project_name
     *
     * @return the value of teacher_horizontal.project_name
     *
     * @mbg.generated
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.project_name
     *
     * @param projectName the value for teacher_horizontal.project_name
     *
     * @mbg.generated
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.host
     *
     * @return the value of teacher_horizontal.host
     *
     * @mbg.generated
     */
    public String getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.host
     *
     * @param host the value for teacher_horizontal.host
     *
     * @mbg.generated
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.participant
     *
     * @return the value of teacher_horizontal.participant
     *
     * @mbg.generated
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.participant
     *
     * @param participant the value for teacher_horizontal.participant
     *
     * @mbg.generated
     */
    public void setParticipant(String participant) {
        this.participant = participant == null ? null : participant.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.approval_unit
     *
     * @return the value of teacher_horizontal.approval_unit
     *
     * @mbg.generated
     */
    public String getApprovalUnit() {
        return approvalUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.approval_unit
     *
     * @param approvalUnit the value for teacher_horizontal.approval_unit
     *
     * @mbg.generated
     */
    public void setApprovalUnit(String approvalUnit) {
        this.approvalUnit = approvalUnit == null ? null : approvalUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.cooperation_unit
     *
     * @return the value of teacher_horizontal.cooperation_unit
     *
     * @mbg.generated
     */
    public String getCooperationUnit() {
        return cooperationUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.cooperation_unit
     *
     * @param cooperationUnit the value for teacher_horizontal.cooperation_unit
     *
     * @mbg.generated
     */
    public void setCooperationUnit(String cooperationUnit) {
        this.cooperationUnit = cooperationUnit == null ? null : cooperationUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.project_time
     *
     * @return the value of teacher_horizontal.project_time
     *
     * @mbg.generated
     */
    public Date getProjectTime() {
        return projectTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.project_time
     *
     * @param projectTime the value for teacher_horizontal.project_time
     *
     * @mbg.generated
     */
    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.amount
     *
     * @return the value of teacher_horizontal.amount
     *
     * @mbg.generated
     */
    public String getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.amount
     *
     * @param amount the value for teacher_horizontal.amount
     *
     * @mbg.generated
     */
    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.remarks
     *
     * @return the value of teacher_horizontal.remarks
     *
     * @mbg.generated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.remarks
     *
     * @param remarks the value for teacher_horizontal.remarks
     *
     * @mbg.generated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.concluding_time
     *
     * @return the value of teacher_horizontal.concluding_time
     *
     * @mbg.generated
     */
    public Date getConcludingTime() {
        return concludingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.concluding_time
     *
     * @param concludingTime the value for teacher_horizontal.concluding_time
     *
     * @mbg.generated
     */
    public void setConcludingTime(Date concludingTime) {
        this.concludingTime = concludingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.actually_task
     *
     * @return the value of teacher_horizontal.actually_task
     *
     * @mbg.generated
     */
    public String getActuallyTask() {
        return actuallyTask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.actually_task
     *
     * @param actuallyTask the value for teacher_horizontal.actually_task
     *
     * @mbg.generated
     */
    public void setActuallyTask(String actuallyTask) {
        this.actuallyTask = actuallyTask == null ? null : actuallyTask.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.operator
     *
     * @return the value of teacher_horizontal.operator
     *
     * @mbg.generated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.operator
     *
     * @param operator the value for teacher_horizontal.operator
     *
     * @mbg.generated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.create_time
     *
     * @return the value of teacher_horizontal.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.create_time
     *
     * @param createTime the value for teacher_horizontal.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_horizontal.modify_time
     *
     * @return the value of teacher_horizontal.modify_time
     *
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_horizontal.modify_time
     *
     * @param modifyTime the value for teacher_horizontal.modify_time
     *
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public String[] getPicId() {
		return picId;
	}

	public void setPicId(String[] picId) {
		this.picId = picId;
	}
    
}
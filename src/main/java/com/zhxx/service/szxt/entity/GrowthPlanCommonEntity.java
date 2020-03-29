package com.zhxx.service.szxt.entity;

import java.util.Date;
import java.util.List;

public class GrowthPlanCommonEntity {
    private String planningId;//形成规划的id
    
    private String studentId;

    private String studenGrade;

    private String commentPersonId;

    private String commentComtent;
    
    private Date createtime;

    private Date updatetime;
   
    private List<GrowthPlanningCommentEntity> growthPlanningCommentEntity;
    private List<GrowthOutputEntity> growthOutputEntityList;
    
    private String planningModelId;
    private String planningOptionId;
    private String planningOptionState;
    private String planningOptionSubmitState;
    
    private String planningModelTitle;
    private String planningModelApplicablegrade;
    private String planningModelCategory;
    private String planningModelModelLevel;
    private String planningModelOptionOrder;
    private String planningModelOptionComment;
    
    private String outputUrl;
    private String outputComtent;
    
    private String studentName;
    private String studentNo;
    
    private String gradeYear;
    
    private String growthPlanningId;//每个规划模型的id
    
	public String getPlanningId() {
		return planningId;
	}
	public void setPlanningId(String planningId) {
		this.planningId = planningId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudenGrade() {
		return studenGrade;
	}
	public void setStudenGrade(String studenGrade) {
		this.studenGrade = studenGrade;
	}
	public String getCommentPersonId() {
		return commentPersonId;
	}
	public void setCommentPersonId(String commentPersonId) {
		this.commentPersonId = commentPersonId;
	}
	public String getCommentComtent() {
		return commentComtent;
	}
	public void setCommentComtent(String commentComtent) {
		this.commentComtent = commentComtent;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public List<GrowthPlanningCommentEntity> getGrowthPlanningCommentEntity() {
		return growthPlanningCommentEntity;
	}
	public void setGrowthPlanningCommentEntity(List<GrowthPlanningCommentEntity> growthPlanningCommentEntity) {
		this.growthPlanningCommentEntity = growthPlanningCommentEntity;
	}
	public String getPlanningModelId() {
		return planningModelId;
	}
	public void setPlanningModelId(String planningModelId) {
		this.planningModelId = planningModelId;
	}
	public String getPlanningOptionId() {
		return planningOptionId;
	}
	public void setPlanningOptionId(String planningOptionId) {
		this.planningOptionId = planningOptionId;
	}
	public String getPlanningOptionState() {
		return planningOptionState;
	}
	public void setPlanningOptionState(String planningOptionState) {
		this.planningOptionState = planningOptionState;
	}
	public String getPlanningOptionSubmitState() {
		return planningOptionSubmitState;
	}
	public void setPlanningOptionSubmitState(String planningOptionSubmitState) {
		this.planningOptionSubmitState = planningOptionSubmitState;
	}
	public String getPlanningModelTitle() {
		return planningModelTitle;
	}
	public void setPlanningModelTitle(String planningModelTitle) {
		this.planningModelTitle = planningModelTitle;
	}
	public String getPlanningModelApplicablegrade() {
		return planningModelApplicablegrade;
	}
	public void setPlanningModelApplicablegrade(String planningModelApplicablegrade) {
		this.planningModelApplicablegrade = planningModelApplicablegrade;
	}
	public String getPlanningModelCategory() {
		return planningModelCategory;
	}
	public void setPlanningModelCategory(String planningModelCategory) {
		this.planningModelCategory = planningModelCategory;
	}
	public String getPlanningModelModelLevel() {
		return planningModelModelLevel;
	}
	public void setPlanningModelModelLevel(String planningModelModelLevel) {
		this.planningModelModelLevel = planningModelModelLevel;
	}
	public String getPlanningModelOptionOrder() {
		return planningModelOptionOrder;
	}
	public void setPlanningModelOptionOrder(String planningModelOptionOrder) {
		this.planningModelOptionOrder = planningModelOptionOrder;
	}
	public String getPlanningModelOptionComment() {
		return planningModelOptionComment;
	}
	public void setPlanningModelOptionComment(String planningModelOptionComment) {
		this.planningModelOptionComment = planningModelOptionComment;
	}
	public String getGrowthPlanningId() {
		return growthPlanningId;
	}
	public void setGrowthPlanningId(String growthPlanningId) {
		this.growthPlanningId = growthPlanningId;
	}
	public String getOutputUrl() {
		return outputUrl;
	}
	public void setOutputUrl(String outputUrl) {
		this.outputUrl = outputUrl;
	}
	public String getOutputComtent() {
		return outputComtent;
	}
	public void setOutputComtent(String outputComtent) {
		this.outputComtent = outputComtent;
	}
	public List<GrowthOutputEntity> getGrowthOutputEntityList() {
		return growthOutputEntityList;
	}
	public void setGrowthOutputEntityList(List<GrowthOutputEntity> growthOutputEntityList) {
		this.growthOutputEntityList = growthOutputEntityList;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getGradeYear() {
		return gradeYear;
	}
	public void setGradeYear(String gradeYear) {
		this.gradeYear = gradeYear;
	}
    
}

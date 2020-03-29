package com.zhxx.service.szxt.entity;

import java.util.Date;
import java.util.List;

public class AccessModelOptionCommonEntity {
	   //model part
	    private String id;
	    private String title;
	    private String description;
	    private String applicablegrade;
	    private String category;
	    private String modelLevel;
	    private String required;
	    private String openCloseState;
	    private String standbyfield1;
	    private String standbyfield2;
	    private String standbyfield3;
	    private String standbyfield4;
	    private String standbyfield5;
	    private String state;
	    private Date createtime;
	    private Date updatetime;
	    private String lastoperator;
	    private String operator;
	    
	    //option part
	    private String modelOptionId;
	    private String modelOptionOrder;
	    private String modelOptionComment;
	    private String modelOptionScore;
	    
	    private List<AccessOptionEntity> optionList;
	    
	    private String userId;
	    
	    private String applicablegradeName;
	    private String categoryName;
	    private String modellevelName;
	    private String requiredName;
	    private String openCloseStateName;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getApplicablegrade() {
			return applicablegrade;
		}
		public void setApplicablegrade(String applicablegrade) {
			this.applicablegrade = applicablegrade;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getModelLevel() {
			return modelLevel;
		}
		public void setModelLevel(String modelLevel) {
			this.modelLevel = modelLevel;
		}
		public String getRequired() {
			return required;
		}
		public void setRequired(String required) {
			this.required = required;
		}
		public String getOpenCloseState() {
			return openCloseState;
		}
		public void setOpenCloseState(String openCloseState) {
			this.openCloseState = openCloseState;
		}
		public String getStandbyfield1() {
			return standbyfield1;
		}
		public void setStandbyfield1(String standbyfield1) {
			this.standbyfield1 = standbyfield1;
		}
		public String getStandbyfield2() {
			return standbyfield2;
		}
		public void setStandbyfield2(String standbyfield2) {
			this.standbyfield2 = standbyfield2;
		}
		public String getStandbyfield3() {
			return standbyfield3;
		}
		public void setStandbyfield3(String standbyfield3) {
			this.standbyfield3 = standbyfield3;
		}
		public String getStandbyfield4() {
			return standbyfield4;
		}
		public void setStandbyfield4(String standbyfield4) {
			this.standbyfield4 = standbyfield4;
		}
		public String getStandbyfield5() {
			return standbyfield5;
		}
		public void setStandbyfield5(String standbyfield5) {
			this.standbyfield5 = standbyfield5;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
		public String getLastoperator() {
			return lastoperator;
		}
		public void setLastoperator(String lastoperator) {
			this.lastoperator = lastoperator;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getModelOptionId() {
			return modelOptionId;
		}
		public void setModelOptionId(String modelOptionId) {
			this.modelOptionId = modelOptionId;
		}
		public String getModelOptionOrder() {
			return modelOptionOrder;
		}
		public void setModelOptionOrder(String modelOptionOrder) {
			this.modelOptionOrder = modelOptionOrder;
		}
		public String getModelOptionComment() {
			return modelOptionComment;
		}
		public void setModelOptionComment(String modelOptionComment) {
			this.modelOptionComment = modelOptionComment;
		}
		public String getModelOptionScore() {
			return modelOptionScore;
		}
		public void setModelOptionScore(String modelOptionScore) {
			this.modelOptionScore = modelOptionScore;
		}
		public List<AccessOptionEntity> getOptionList() {
			return optionList;
		}
		public void setOptionList(List<AccessOptionEntity> optionList) {
			this.optionList = optionList;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getApplicablegradeName() {
			return applicablegradeName;
		}
		public void setApplicablegradeName(String applicablegradeName) {
			this.applicablegradeName = applicablegradeName;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getModellevelName() {
			return modellevelName;
		}
		public void setModellevelName(String modellevelName) {
			this.modellevelName = modellevelName;
		}
		public String getRequiredName() {
			return requiredName;
		}
		public void setRequiredName(String requiredName) {
			this.requiredName = requiredName;
		}
		public String getOpenCloseStateName() {
			return openCloseStateName;
		}
		public void setOpenCloseStateName(String openCloseStateName) {
			this.openCloseStateName = openCloseStateName;
		}
	    
	   
}

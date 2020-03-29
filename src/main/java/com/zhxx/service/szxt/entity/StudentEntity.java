package com.zhxx.service.szxt.entity;

import java.util.Date;
import java.util.List;

public class StudentEntity {
    //删除使用，其他不用
    private String mMarkId;  //mark_id
    private String aSolutionID;  //solution_id
    private String aProblemId;  //problem_id
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_id
     *
     * @mbg.generated
     */
    private String studentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_no
     *
     * @mbg.generated
     */
    private String studentNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_name
     *
     * @mbg.generated
     */
    private String studentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_sex
     *
     * @mbg.generated
     */
    private String studentSex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_age
     *
     * @mbg.generated
     */
    private Integer studentAge;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_phone_number
     *
     * @mbg.generated
     */
    private String studentPhoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.student_grade
     *
     * @mbg.generated
     */
    private Integer studentGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.create_time
     *
     * @mbg.generated
     */
    private String studentBindingCode;
    private int studentBinding;
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.major_id
     *
     * @mbg.generated
     */
    private String majorId;
    private String majorName;
    private String startSchool;
    private String headImg;
    private String headImgPath;


    public String getmMarkId() {
        return mMarkId;
    }

    public void setmMarkId(String mMarkId) {
        this.mMarkId = mMarkId;
    }

    public String getaSolutionID() {
        return aSolutionID;
    }

    public void setaSolutionID(String aSolutionID) {
        this.aSolutionID = aSolutionID;
    }

    public String getaProblemId() {
        return aProblemId;
    }

    public void setaProblemId(String aProblemId) {
        this.aProblemId = aProblemId;
    }

    public int getStudentBinding() {
        return studentBinding;
    }

    public void setStudentBinding(int studentBinding) {
        this.studentBinding = studentBinding;
    }

    public String getStudentBindingCode() {
        return studentBindingCode;
    }

    public void setStudentBindingCode(String studentBindingCode) {
        this.studentBindingCode = studentBindingCode;
    }

    public String getStartSchool() {
        return startSchool;
    }

    public void setStartSchool(String startSchool) {
        this.startSchool = startSchool;
    }

    public Integer getRetardation() {
        return retardation;
    }

    public void setRetardation(Integer retardation) {
        this.retardation = retardation;
    }

    private Integer retardation;

    private List<MajorEntity> majorEntities;

    public List<MajorEntity> getMajorEntities() {
        return majorEntities;
    }

    public void setMajorEntities(List<MajorEntity> majorEntities) {
        this.majorEntities = majorEntities;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_id
     *
     * @return the value of student.student_id
     * @mbg.generated
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_id
     *
     * @param studentId the value for student.student_id
     * @mbg.generated
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_no
     *
     * @return the value of student.student_no
     * @mbg.generated
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_no
     *
     * @param studentNo the value for student.student_no
     * @mbg.generated
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_name
     *
     * @return the value of student.student_name
     * @mbg.generated
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_name
     *
     * @param studentName the value for student.student_name
     * @mbg.generated
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_sex
     *
     * @return the value of student.student_sex
     * @mbg.generated
     */
    public String getStudentSex() {
        return studentSex;
    }
     
    public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_sex
     *
     * @param studentSex the value for student.student_sex
     * @mbg.generated
     */
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex == null ? null : studentSex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_age
     *
     * @return the value of student.student_age
     * @mbg.generated
     */
    public Integer getStudentAge() {
        return studentAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_age
     *
     * @param studentAge the value for student.student_age
     * @mbg.generated
     */
    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_phone_number
     *
     * @return the value of student.student_phone_number
     * @mbg.generated
     */
    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_phone_number
     *
     * @param studentPhoneNumber the value for student.student_phone_number
     * @mbg.generated
     */
    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber == null ? null : studentPhoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.student_grade
     *
     * @return the value of student.student_grade
     * @mbg.generated
     */
    public Integer getStudentGrade() {
        return studentGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.student_grade
     *
     * @param studentGrade the value for student.student_grade
     * @mbg.generated
     */
    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.create_time
     *
     * @return the value of student.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.create_time
     *
     * @param createTime the value for student.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.modify_time
     *
     * @return the value of student.modify_time
     * @mbg.generated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.modify_time
     *
     * @param modifyTime the value for student.modify_time
     * @mbg.generated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.status
     *
     * @return the value of student.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.status
     *
     * @param status the value for student.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.major_id
     *
     * @return the value of student.major_id
     * @mbg.generated
     */
    public String getMajorId() {
        return majorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.major_id
     *
     * @param majorId the value for student.major_id
     * @mbg.generated
     */
    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    private List<MajorEntity> majorEntity;

    public List<MajorEntity> getMajorEntity() {
        return majorEntity;
    }

    public void setMajorEntity(List<MajorEntity> majorEntity) {
        this.majorEntity = majorEntity;
    }

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
    
}
package com.zhxx.service.szxt.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zhxx.admin.server.dto.UserDto;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.entity.TeacherAchievementsDictEntity;
import com.zhxx.service.szxt.entity.TeacherConcludingEntity;
import com.zhxx.service.szxt.entity.TeacherHorizontalEntity;
import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherPatentEntity;
import com.zhxx.service.szxt.entity.TeacherPrizeEntity;
import com.zhxx.service.szxt.entity.TeacherProjectEntity;
import com.zhxx.service.szxt.entity.TeacherResearchSectionEntity;
import com.zhxx.service.szxt.entity.TeacherStudentRelateEntity;
import com.zhxx.service.szxt.entity.TeacherUserEntity;
import com.zhxx.service.szxt.entity.TeacherWritingsEntity;

public interface TeacherService {


	public HttpResponseEntity addResearch(TeacherResearchSectionEntity teacherResearchSectionEntity);
	public HttpResponseEntity delResearch(TeacherResearchSectionEntity teacherResearchSectionEntity);
	public List<TeacherResearchSectionEntity> selResearch();
	public List<TeacherResearchSectionEntity> selResearchParent();
	public List<TeacherResearchSectionEntity> miniselResearch(TeacherResearchSectionEntity teacherResearchSectionEntity);
	
	public List<TeacherAchievementsDictEntity> achievementsDict(TeacherAchievementsDictEntity teacherAchievementsDictEntity);
	public HttpResponseEntity addTeacher(TeacherUserEntity teacherUserEntity);
	public HttpResponseEntity modifyTeacher(TeacherUserEntity teacherUserEntity);
	public TeacherUserEntity selTeacher(TeacherUserEntity teacherUserEntity);
	public List<TeacherUserEntity> selTeacherList(TeacherUserEntity teacherUserEntity);
	public List<TeacherUserEntity> selTeacherOutputList(TeacherUserEntity teacherUserEntity);
	public HttpResponseEntity downloadTeacherOutput(TeacherUserEntity teacherUserEntity,HttpServletResponse response);
	
	public HttpResponseEntity delTeacher(TeacherUserEntity teacherUserEntity);
	public HttpResponseEntity setPermission(TeacherUserEntity teacherUserEntity);
	
	public HttpResponseEntity bindStudent(TeacherStudentRelateEntity teacherStudentRelateEntity);
	public HttpResponseEntity unbindStudent(TeacherStudentRelateEntity teacherStudentRelateEntity);
	public List<StudentEntity> bindStudentList(TeacherStudentRelateEntity teacherStudentRelateEntity);
	public List<StudentEntity> queryStudentMsg ( StudentEntity studentEntity);
	
	public HttpResponseEntity addPaper(TeacherPaperEntity teacherPaperEntity);
	public HttpResponseEntity modifyPaper(TeacherPaperEntity teacherPaperEntity);
	public HttpResponseEntity delPaper(TeacherPaperEntity teacherPaperEntity);
	public List<TeacherPaperEntity> selPaper(TeacherPaperEntity teacherPaperEntity);
	
	public HttpResponseEntity addWritings(TeacherWritingsEntity teacherWritingsEntity);
	public HttpResponseEntity modifyWritings(TeacherWritingsEntity teacherWritingsEntity);
	public HttpResponseEntity delWritings(TeacherWritingsEntity teacherWritingsEntity);
	public List<TeacherWritingsEntity> selWritings(TeacherWritingsEntity teacherWritingsEntity);
	
	public HttpResponseEntity addProject(TeacherProjectEntity teacherProjectEntity);
	public HttpResponseEntity modifyProject(TeacherProjectEntity teacherProjectEntity);
	public HttpResponseEntity delProject(TeacherProjectEntity teacherProjectEntity);
	public List<TeacherProjectEntity> selProject(TeacherProjectEntity teacherProjectEntity);
	
	public HttpResponseEntity addConcluding(TeacherConcludingEntity teacherConcludingEntity);
	public HttpResponseEntity modifyConcluding(TeacherConcludingEntity teacherConcludingEntity);
	public HttpResponseEntity delConcluding(TeacherConcludingEntity teacherConcludingEntity);
	public List<TeacherConcludingEntity> selConcluding(TeacherConcludingEntity teacherConcludingEntity);
	
	public HttpResponseEntity addHorizontal(TeacherHorizontalEntity teacherHorizontalEntity);
	public HttpResponseEntity modifyHorizontal(TeacherHorizontalEntity teacherHorizontalEntity);
	public HttpResponseEntity delHorizontal(TeacherHorizontalEntity teacherHorizontalEntity);
	public List<TeacherHorizontalEntity> selHorizontal(TeacherHorizontalEntity teacherHorizontalEntity);
	
	public HttpResponseEntity addPrize(TeacherPrizeEntity teacherPrizeEntity);
	public HttpResponseEntity modifyPrize(TeacherPrizeEntity teacherPrizeEntity);
	public HttpResponseEntity delPrize(TeacherPrizeEntity teacherPrizeEntity);
	public List<TeacherPrizeEntity> selPrize(TeacherPrizeEntity teacherPrizeEntity);
	
	public HttpResponseEntity addPatent(TeacherPatentEntity teacherPatentEntity);
	public HttpResponseEntity modifyPatent(TeacherPatentEntity teacherPatentEntity);
	public HttpResponseEntity delPatent(TeacherPatentEntity teacherPatentEntity);
	public List<TeacherPatentEntity> selPatent(TeacherPatentEntity teacherPatentEntity);
}

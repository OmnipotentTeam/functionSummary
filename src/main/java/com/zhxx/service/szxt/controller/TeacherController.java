package com.zhxx.service.szxt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
import com.zhxx.service.szxt.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	/**
	 * pc端学院和教研室添加
	 */
	@RequestMapping(value = "/addResearch", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public HttpResponseEntity addResearch(@RequestBody TeacherResearchSectionEntity teacherResearchSectionEntity){
		 return teacherService.addResearch(teacherResearchSectionEntity);
	}
	/**
	 * pc端学院和教研室删除
	 */
	@RequestMapping(value = "/delResearch", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public HttpResponseEntity delResearch(@RequestBody TeacherResearchSectionEntity teacherResearchSectionEntity){
		 return teacherService.delResearch(teacherResearchSectionEntity);
	}
	/**
	 * pc端学院和教研室列表展示
	 */
	@RequestMapping(value = "/selResearch", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public List<TeacherResearchSectionEntity> selResearch(){
		 return teacherService.selResearch();
	}
	/**
	 * 小程序学院和教研室
	 */
	@RequestMapping(value = "/miniselResearch", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public List<TeacherResearchSectionEntity> miniselResearch(@RequestBody TeacherResearchSectionEntity teacherResearchSectionEntity){
		 return teacherService.miniselResearch(teacherResearchSectionEntity);
	}
	/**
	 * pc端学院和教研室列表展示
	 */
	@RequestMapping(value = "/selResearchParent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public List<TeacherResearchSectionEntity> selResearchParent(){
		 return teacherService.selResearchParent();
	}
	
	/**
	 * 字典信息
	 */
	@RequestMapping(value = "/achievementsDict", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
	public List<TeacherAchievementsDictEntity> achievementsDict(@RequestBody TeacherAchievementsDictEntity teacherAchievementsDictEntity){
		 return teacherService.achievementsDict(teacherAchievementsDictEntity);
	}
     /**
      * 教师信息添加
      */
	@RequestMapping(value = "/addTeacher", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addTeacher(@RequestBody TeacherUserEntity teacherUserEntity) {
        return teacherService.addTeacher(teacherUserEntity);
    }
	/**
     * 教师信息修改
     */
   @RequestMapping(value = "/modifyTeacher", method = RequestMethod.POST, headers = "Accept=application/json")
   @ResponseBody
   public HttpResponseEntity modifyTeacher(@RequestBody TeacherUserEntity teacherUserEntity) {
       return teacherService.modifyTeacher(teacherUserEntity);
   }
	/**
     * 教师信息查询
     */
	@RequestMapping(value = "/selTeacher", method = RequestMethod.POST, headers = "Accept=application/json")
   @ResponseBody
   public TeacherUserEntity selTeacher(@RequestBody TeacherUserEntity teacherUserEntity) {
       return teacherService.selTeacher(teacherUserEntity);
   }
	/**
     * pc端教师信息查询（根据学院内容查询）
     */
   @RequestMapping(value = "/selTeacherList", method = RequestMethod.POST, headers = "Accept=application/json")
   @ResponseBody
   public List<TeacherUserEntity> selTeacherList(@RequestBody TeacherUserEntity teacherUserEntity) {
       return teacherService.selTeacherList(teacherUserEntity);
   }
	/**
     * pc端教师成果物信息查询（根据学院内容查询）
     */
   @RequestMapping(value = "/selTeacherOutputList", method = RequestMethod.POST, headers = "Accept=application/json")
   @ResponseBody
   public List<TeacherUserEntity> selTeacherOutputList(@RequestBody TeacherUserEntity teacherUserEntity) {
       return teacherService.selTeacherOutputList(teacherUserEntity);
   }
   /**
    * pc端教师成果物信息下载（根据学院内容查询）
    */
  @RequestMapping(value = "/downloadTeacherOutput", method = RequestMethod.GET, headers = "Accept=application/json")
  @ResponseBody
  public HttpResponseEntity downloadTeacherOutput(HttpServletResponse response) {
      TeacherUserEntity teacherUserEntity = new TeacherUserEntity();
      return teacherService.downloadTeacherOutput(teacherUserEntity,response);
  }
	/**
	 * 删除教师
	 */
	@RequestMapping(value = "/delTeacher", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delTeacher(@RequestBody TeacherUserEntity eacherUserEntity) {
        return teacherService.delTeacher(eacherUserEntity);
    }
	/**
	 * 设定教师权限
	 */
	@RequestMapping(value = "/setPermission", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity setPermission(@RequestBody TeacherUserEntity eacherUserEntity) {
        return teacherService.setPermission(eacherUserEntity);
    }
	/**
	 * 教师绑定学生
	 */
	@RequestMapping(value = "/bindStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity bindStudent(@RequestBody TeacherStudentRelateEntity teacherStudentRelateEntity) {
        return teacherService.bindStudent(teacherStudentRelateEntity);
    }
	/**
	 * 教师删除绑定学生
	 */
	@RequestMapping(value = "/unbindStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity unbindStudent(@RequestBody TeacherStudentRelateEntity teacherStudentRelateEntity) {
        return teacherService.unbindStudent(teacherStudentRelateEntity);
    }
	/**
	 * 教师显示绑定学生列表
	 */
	@RequestMapping(value = "/bindStudentList", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<StudentEntity> bindStudentList(@RequestBody TeacherStudentRelateEntity teacherStudentRelateEntity) {
        return teacherService.bindStudentList(teacherStudentRelateEntity);
    }
	/**
	 * 教师查询学生详细信息
	 */
	@RequestMapping(value = "/queryStudentMsg", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<StudentEntity> queryStudentMsg(@RequestBody  StudentEntity studentEntity) {
        return teacherService.queryStudentMsg(studentEntity);
    }
	/**
	 * 成果物--论文
	 * 新增
	 */
	@RequestMapping(value = "/addPaper", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addPaper(@RequestBody TeacherPaperEntity teacherPaperEntity) {
        return teacherService.addPaper(teacherPaperEntity);
    }
	/**
	 * 成果物--论文
	 * 修改
	 */
	@RequestMapping(value = "/modifyPaper", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyPaper(@RequestBody TeacherPaperEntity teacherPaperEntity) {
        return teacherService.modifyPaper(teacherPaperEntity);
    }
	/**
	 * 成果物--论文
	 * 删除
	 */
	@RequestMapping(value = "/delPaper", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delPaper(@RequestBody TeacherPaperEntity teacherPaperEntity) {
        return teacherService.delPaper(teacherPaperEntity);
    }
	/**
	 * 成果物--论文
	 * 查询
	 */
	@RequestMapping(value = "/selPaper", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherPaperEntity> selPaper(@RequestBody TeacherPaperEntity teacherPaperEntity) {
        return teacherService.selPaper(teacherPaperEntity);
    }
	/**
	 * 成果物--著作
	 * 新增
	 */
	@RequestMapping(value = "/addWritings", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addWritings(@RequestBody TeacherWritingsEntity teacherWritingsEntity) {
        return teacherService.addWritings(teacherWritingsEntity);
    }
	/**
	 * 成果物--著作
	 * 修改
	 */
	@RequestMapping(value = "/modifyWritings", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyWritings(@RequestBody TeacherWritingsEntity teacherWritingsEntity) {
        return teacherService.modifyWritings(teacherWritingsEntity);
    }
	/**
	 * 成果物--著作
	 * 删除
	 */
	@RequestMapping(value = "/delWritings", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delWritings(@RequestBody TeacherWritingsEntity teacherWritingsEntity) {
        return teacherService.delWritings(teacherWritingsEntity);
    }
	/**
	 * 成果物--著作
	 * 查询
	 */
	@RequestMapping(value = "/selWritings", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherWritingsEntity> selWritings(@RequestBody TeacherWritingsEntity teacherWritingsEntity) {
        return teacherService.selWritings(teacherWritingsEntity);
    }
	/**
	 * 成果物--立项课题
	 * 新增
	 */
	@RequestMapping(value = "/addProject", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addProject(@RequestBody TeacherProjectEntity teacherProjectEntity) {
        return teacherService.addProject(teacherProjectEntity);
    }
	/**
	 * 成果物--立项课题
	 * 修改
	 */
	@RequestMapping(value = "/modifyProject", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyProject(@RequestBody TeacherProjectEntity teacherProjectEntity) {
        return teacherService.modifyProject(teacherProjectEntity);
    }
	/**
	 * 成果物--立项课题
	 * 删除
	 */
	@RequestMapping(value = "/delProject", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delProject(@RequestBody TeacherProjectEntity teacherProjectEntity) {
        return teacherService.delProject(teacherProjectEntity);
    }
	/**
	 * 成果物--立项课题
	 * 查询
	 */
	@RequestMapping(value = "/selProject", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherProjectEntity> selProject(@RequestBody TeacherProjectEntity teacherProjectEntity) {
        return teacherService.selProject(teacherProjectEntity);
    }
	/**
	 * 成果物--结题课题
	 * 新增
	 */
	@RequestMapping(value = "/addConcluding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addConcluding(@RequestBody TeacherConcludingEntity teacherConcludingEntity) {
        return teacherService.addConcluding(teacherConcludingEntity);
    }
	/**
	 * 成果物--结题课题
	 * 修改
	 */
	@RequestMapping(value = "/modifyConcluding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyConcluding(@RequestBody TeacherConcludingEntity teacherConcludingEntity) {
        return teacherService.modifyConcluding(teacherConcludingEntity);
    }
	/**
	 * 成果物--结题课题
	 * 删除
	 */
	@RequestMapping(value = "/delConcluding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delConcluding(@RequestBody TeacherConcludingEntity teacherConcludingEntity) {
        return teacherService.delConcluding(teacherConcludingEntity);
    }
	/**
	 * 成果物--结题课题
	 * 查询
	 */
	@RequestMapping(value = "/selConcluding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherConcludingEntity> selConcluding(@RequestBody TeacherConcludingEntity teacherConcludingEntity) {
        return teacherService.selConcluding(teacherConcludingEntity);
    }
	/**
	 * 成果物--横向课题
	 * 新增
	 */
	@RequestMapping(value = "/addHorizontal", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addHorizontal(@RequestBody TeacherHorizontalEntity teacherHorizontalEntity) {
        return teacherService.addHorizontal(teacherHorizontalEntity);
    }
	/**
	 * 成果物--横向课题
	 * 修改
	 */
	@RequestMapping(value = "/modifyHorizontal", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyHorizontal(@RequestBody TeacherHorizontalEntity teacherHorizontalEntity) {
        return teacherService.modifyHorizontal(teacherHorizontalEntity);
    }
	/**
	 * 成果物--横向课题
	 * 删除
	 */
	@RequestMapping(value = "/delHorizontal", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delHorizontal(@RequestBody TeacherHorizontalEntity teacherHorizontalEntity) {
        return teacherService.delHorizontal(teacherHorizontalEntity);
    }
	/**
	 * 成果物--横向课题
	 * 删除
	 */
	@RequestMapping(value = "/selHorizontal", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherHorizontalEntity> selHorizontal(@RequestBody TeacherHorizontalEntity teacherHorizontalEntity) {
        return teacherService.selHorizontal(teacherHorizontalEntity);
    }
	/**
	 * 成果物--获奖
	 * 新增
	 */
	@RequestMapping(value = "/addPrize", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addPrize(@RequestBody TeacherPrizeEntity teacherPrizeEntity) {
        return teacherService.addPrize(teacherPrizeEntity);
    }
	/**
	 * 成果物--获奖
	 * 修改
	 */
	@RequestMapping(value = "/modifyPrize", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyPrize(@RequestBody TeacherPrizeEntity teacherPrizeEntity) {
        return teacherService.modifyPrize(teacherPrizeEntity);
    }
	/**
	 * 成果物--获奖
	 * 删除
	 */
	@RequestMapping(value = "/delPrize", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delPrize(@RequestBody TeacherPrizeEntity teacherPrizeEntity) {
        return teacherService.delPrize(teacherPrizeEntity);
    }
	/**
	 * 成果物--获奖
	 * 查询
	 */
	@RequestMapping(value = "/selPrize", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherPrizeEntity> selPrize(@RequestBody TeacherPrizeEntity teacherPrizeEntity) {
        return teacherService.selPrize(teacherPrizeEntity);
    }
	/**
	 * 成果物--专利
	 * 新增
	 */
	@RequestMapping(value = "/addPatent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addPatent(@RequestBody TeacherPatentEntity teacherPatentEntity) {
        return teacherService.addPatent(teacherPatentEntity);
    }
	/**
	 * 成果物--专利
	 * 修改
	 */
	@RequestMapping(value = "/modifyPatent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity modifyPatent(@RequestBody TeacherPatentEntity teacherPatentEntity) {
        return teacherService.modifyPatent(teacherPatentEntity);
    }
	/**
	 * 成果物--专利
	 * 删除
	 */
	@RequestMapping(value = "/delPatent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity delPatent(@RequestBody TeacherPatentEntity teacherPatentEntity) {
        return teacherService.delPatent(teacherPatentEntity);
    }
	/**
	 * 成果物--专利
	 * 查询
	 */
	@RequestMapping(value = "/selPatent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<TeacherPatentEntity> selPatent(@RequestBody TeacherPatentEntity teacherPatentEntity) {
        return teacherService.selPatent(teacherPatentEntity);
    }
}

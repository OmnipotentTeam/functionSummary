package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.GradeEvaluationEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;
import com.zhxx.service.szxt.entity.SysUserEntity;
import com.zhxx.service.szxt.service.GradeEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by huasheng on 2018/12/5.
 */
@Controller
@RequestMapping("/grade")
public class GradeEvaluationController {
    @Autowired
    private GradeEvaluationService gradeEvaluationService;


    /**
     * 学生端学生查询试卷预览（包括入学评测与阶段评测类型）
     * 需要参数grade（学年）
     * 韦良伟
     *
     * @param gradeEvaluationEntity
     * @return
     */
    @RequestMapping(value = "/problemAndSolutionStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity problemAndSolutionStudent(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.problemAndSolutionStudent(gradeEvaluationEntity);
    }

    /**
     * 年级测评，根据条件查询年级测评列表
     *
     * @param gradeEvaluationEntity
     * @return
     * @author:陈志威 传入参数："pageNum":int,(必传)
     * "pageSize":int,(必传)
     * "state":String ("已结束" ,"进行中"),
     * "evaluationName":String,
     * "evaluationType":int,(0,1)
     * "questionBankName":String,
     * "username":String,
     * "grade":int (1,2,3)(必传)
     * }
     * @date2018/12/7
     */
    @RequestMapping(value = "/selectGradeEvaluation", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectGradeEvaluation(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.selectGradeEvaluation(gradeEvaluationEntity);
    }

    /**
     * 查询全部未发布评测
     * 传入参数："pageNum":int,(必传)
     * "pageSize":int,(必传)
     * }
     *
     * @param gradeEvaluationEntity
     * @return
     */
    @RequestMapping(value = "/selectAllGradeEvaluationDraft", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectUnpublished(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.selectUnpublished(gradeEvaluationEntity);
    }

    /**
     * 查询全部试题字典表
     * 传入参数：无
     *
     * @return
     */
    @RequestMapping(value = "/selectQuestion", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectQuestion(@RequestBody QuestionBankEntity questionBankEntity) {
        return gradeEvaluationService.selectQuestion(questionBankEntity);
    }

    /**
     * 查询全部试发布人字典表
     * 传入参数：无
     *
     * @return
     */
    @RequestMapping(value = "/selectSysUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectSysUser() {
        return gradeEvaluationService.selectSysUser();
    }

    /**
     * 查询登录用户id
     * 传入参数：username:String
     *
     * @return
     */
    @RequestMapping(value = "/selectUserId", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectUserId(@RequestBody SysUserEntity sysUserEntity) {
        return gradeEvaluationService.selectUserId(sysUserEntity);
    }

    /**
     * 修改发布状态
     * 传入参数：gradeEvaluationId：String;
     *
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateStatus(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.updateStatus(gradeEvaluationEntity);
    }

    /**
     * 删除评测草稿
     * 传入参数：gradeEvaluationId：String;
     *
     * @param gradeEvaluationEntity
     * @return
     */
    @RequestMapping(value = "/deleteGradeEvaluationDraft", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity deleteGradeEvaluationDraft(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.deleteGradeEvaluationDraft(gradeEvaluationEntity);
    }

    /**
     * 添加评测草稿
     * 传入参数：
     * evaluationName:String,
     * evaluationType:int,
     * questionId:int,
     * evaluationDate:Date,
     * userId:int,
     * grade:int
     *
     * @param gradeEvaluationEntity
     * @return
     */
    @RequestMapping(value = "/addGradeEvaluationDraft", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addGradeEvaluationDraft(@RequestBody GradeEvaluationEntity gradeEvaluationEntity) {
        return gradeEvaluationService.addGradeEvaluationDraft(gradeEvaluationEntity);
    }
}

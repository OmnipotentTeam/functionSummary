package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.GradeEvaluationEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;
import com.zhxx.service.szxt.entity.SysUserEntity;

import javax.servlet.http.HttpSession;


/**
 * Created by huasheng on 2018/12/5.
 */

public interface GradeEvaluationService {

    public HttpResponseEntity problemAndSolutionStudent(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity selectGradeEvaluation(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity selectUnpublished(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity selectQuestion(QuestionBankEntity questionBankEntity);

    HttpResponseEntity selectSysUser();

    HttpResponseEntity updateStatus(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity deleteGradeEvaluationDraft(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity addGradeEvaluationDraft(GradeEvaluationEntity gradeEvaluationEntity);

    HttpResponseEntity selectUserId(SysUserEntity sysUserEntity);
}

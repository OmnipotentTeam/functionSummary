package com.zhxx.service.szxt.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.admin.server.model.User;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.GradeEvaluationEntity;
import com.zhxx.service.szxt.entity.ProblemEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;
import com.zhxx.service.szxt.entity.SysUserEntity;
import com.zhxx.service.szxt.mapper.GradeEvaluationEntityMapper;
import com.zhxx.service.szxt.mapper.QuestionBankEntityMapper;
import com.zhxx.service.szxt.mapper.SysUserEntityMapper;
import com.zhxx.service.szxt.service.GradeEvaluationService;
import com.zhxx.service.szxt.service.QuestionBankService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by huasheng on 2018/12/5.
 */
@Service
public class GradeEvaluationServiceImpl implements GradeEvaluationService {
    private final Logger logger = LoggerFactory.getLogger(GradeEvaluationService.class);
    @Autowired
    private GradeEvaluationEntityMapper gradeEvaluationEntityMapper;
    @Autowired
    private QuestionBankEntityMapper questionBankEntityMapper;
    @Autowired
    private SysUserEntityMapper sysUserEntityMapper;


    /**
     * 学生查询试卷预览
     *
     * @param gradeEvaluationEntity
     * @return
     */
    public HttpResponseEntity problemAndSolutionStudent(GradeEvaluationEntity gradeEvaluationEntity) {
        try {

            if (gradeEvaluationEntity.getGrade() == 1) {   //如果是入学时
                gradeEvaluationEntity.setGrade(1);
                gradeEvaluationEntity.setEvaluationType(0);
            } else {                                       //正常阶段
                gradeEvaluationEntity.setEvaluationType(1);
            }
            List<ProblemEntity> list = gradeEvaluationEntityMapper.problemAndSolutionStudent(gradeEvaluationEntity);
            logger.info("试题查询成功，接口{}", "problemAndSolutionStudent");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("试题查询失败，接口{}", "problemAndSolutionStudent");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 根据条件查询年级测评列表
     *
     * @param gradeEvaluationEntity
     * @return
     */
    public HttpResponseEntity selectGradeEvaluation(GradeEvaluationEntity gradeEvaluationEntity) {
        try {
            if (StringUtils.isEmpty(gradeEvaluationEntity.getEvaluationName())) {
                gradeEvaluationEntity.setEvaluationName(null);
            }
            if (StringUtils.isEmpty(gradeEvaluationEntity.getState())) {
                gradeEvaluationEntity.setState(null);
            }
            if (StringUtils.isEmpty(gradeEvaluationEntity.getEvaluationType())) {
                gradeEvaluationEntity.setEvaluationType(null);
            }
            if (StringUtils.isEmpty(gradeEvaluationEntity.getQuestionBankName())) {
                gradeEvaluationEntity.setQuestionBankName(null);
            }
            if (StringUtils.isEmpty(gradeEvaluationEntity.getUsername())) {
                gradeEvaluationEntity.setUsername(null);
            }
            if (StringUtils.isEmpty(gradeEvaluationEntity.getGrade())) {
                gradeEvaluationEntity.setGrade(1);
            }
            int pageNum = gradeEvaluationEntity.getPageNum();
            int pageSize = gradeEvaluationEntity.getPageSize();
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            List<GradeEvaluationEntity> list = gradeEvaluationEntityMapper.selectVague(gradeEvaluationEntity);
            PageInfo pageInfo = new PageInfo(list);
            logger.info("年级测评列表查询成功，接口{}", "selectGradeEvaluation");
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("年级测评列表查询失败，接口{}", "selectGradeEvaluation");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 查询全部未发布评测
     *
     * @param gradeEvaluationEntity
     * @return
     */
    public HttpResponseEntity selectUnpublished(GradeEvaluationEntity gradeEvaluationEntity) {
        try {
            int pageNum = gradeEvaluationEntity.getPageNum();
            int pageSize = gradeEvaluationEntity.getPageSize();
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            List<GradeEvaluationEntity> list = gradeEvaluationEntityMapper.selectUnpublished(gradeEvaluationEntity);
            PageInfo pageInfo = new PageInfo(list);
            logger.info("未发布评测查询成功，接口{}", "selectAllGradeEvaluationDraft");
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("未发布评测查询失败，接口{}", "selectAllGradeEvaluationDraft");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 查询全部试题
     *
     * @return
     */
    public HttpResponseEntity selectQuestion(QuestionBankEntity questionBankEntity) {
        try {
            int grade = questionBankEntity.getQuestionBankStage();
            List<QuestionBankEntity> list = questionBankEntityMapper.selectByQuestionBankStage(grade);
            logger.info("试题查询成功，接口{}", "selectQuestion");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("试题查询失败，接口{}", "selectQuestion");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 修改发布状态
     *
     * @param gradeEvaluationEntity
     * @return
     */
    public HttpResponseEntity updateStatus(GradeEvaluationEntity gradeEvaluationEntity) {
        try {

            Date date = new Date();
            GradeEvaluationEntity gradeEvaluationEntity1 = gradeEvaluationEntityMapper.selectByPrimaryKey(gradeEvaluationEntity.getGradeEvaluationId());
            List<GradeEvaluationEntity> gradeEvaluationEntityList = gradeEvaluationEntityMapper.selectGradeEvaluationAll(gradeEvaluationEntity1.getGrade());
            if (gradeEvaluationEntityList.size() == 0) {
                int i = gradeEvaluationEntityMapper.updateStatus(gradeEvaluationEntity);
                if (i > 0) {
                    logger.info("修改发布状态成功，接口{}", "updateStatus");
                    return HttpResponseEntity.seccuss(gradeEvaluationEntity);
                } else {
                    logger.info("修改发布状态失败，接口{}", "updateStatus");
                    return HttpResponseEntity.error("请求失败");
                }

            } else if (gradeEvaluationEntityList.size() > 0) {
                logger.info("修改发布状态失败已有评测在进行，接口{}", "updateStatus");
                return HttpResponseEntity.seccuss(Const.PublicCode.ADDError.getCode());
            }
//            Date endTime = gradeEvaluationEntity1.getEvaluationDate();
//            if (date.before(endTime)){
//
//                gradeEvaluationEntityMapper.updateStatus(gradeEvaluationEntity);
//                logger.info("修改发布状态成功，接口{}", "updateStatus");
//
//            }
            else {

                logger.info("修改发布状态失败，接口{}", "updateStatus");
                return HttpResponseEntity.error("请求失败");

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("修改发布状态失败，接口{}", "updateStatus");
            return HttpResponseEntity.error("请求失败");
        }

    }

    /**
     * 通过评测id删除评测草稿
     *
     * @param gradeEvaluationEntity
     * @return
     */
    public HttpResponseEntity deleteGradeEvaluationDraft(GradeEvaluationEntity gradeEvaluationEntity) {
        try {
            String gradeEvaluationId = gradeEvaluationEntity.getGradeEvaluationId();
            int list = gradeEvaluationEntityMapper.deleteGradeEvaluationDraft(gradeEvaluationId);
            logger.info("删除成功，接口{}", "deleteGradeEvaluationDraft");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除失败，接口{}", "deleteGradeEvaluationDraft");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 查询全部发布人
     *
     * @return
     */
    public HttpResponseEntity selectSysUser() {
        try {
            List<SysUserEntity> list = sysUserEntityMapper.selectSysUser();
            logger.info("发布人查询成功，接口{}", "selectSysUser");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发布人查询失败，接口{}", "selectSysUser");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 查询登录用户id
     *
     * @return
     */
    public HttpResponseEntity selectUserId(SysUserEntity sysUserEntity) {
        try {
            String username = sysUserEntity.getUsername();
            int list = sysUserEntityMapper.selectUserId(username);
            logger.info("发布人查询成功，接口{}", "selectSysUser");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发布人查询失败，接口{}", "selectSysUser");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 添加评测草稿
     *
     * @return
     */
    public HttpResponseEntity addGradeEvaluationDraft(GradeEvaluationEntity gradeEvaluationEntity) {
        try {
            Date date = new Date();
            gradeEvaluationEntity.setGradeEvaluationId(UUIDUtil.getOneUUID());
            gradeEvaluationEntity.setEvaluationStatus(0);
            gradeEvaluationEntity.setCreateTime(date);
            gradeEvaluationEntity.setModifyTime(date);
            gradeEvaluationEntity.setStatus(1);

            gradeEvaluationEntityMapper.insert(gradeEvaluationEntity);
            logger.info("发布人查询成功，接口{}", "addGradeEvaluationDraft");
            return HttpResponseEntity.seccuss("请求成功");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发布人查询失败，接口{}", "addGradeEvaluationDraft");
            return HttpResponseEntity.error("请求失败");
        }
    }

}

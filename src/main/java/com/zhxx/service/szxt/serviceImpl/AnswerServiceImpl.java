package com.zhxx.service.szxt.serviceImpl;


import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.*;
import com.zhxx.service.szxt.mapper.*;
import com.zhxx.service.szxt.service.AnswerService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by huasheng on 2018/12/6.
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    private final Logger logger = LoggerFactory.getLogger(AnswerService.class);
    @Autowired
    private AnswerEntityMapper answerEntityMapper;
    @Autowired
    private GradeEvaluationEntityMapper gradeEvaluationEntityMapper;
    @Autowired
    private MarkEntityMapper markEntityMapper;
    @Autowired
    private DimensionRecordEntityMapper dimensionRecordEntityMapper;
    @Autowired
    private StudentEntityMapper studentEntityMapper;
    @Autowired
    private ProblemEntityMapper problemEntityMapper;
    @Autowired
    private SolutionEntityMapper solutionEntityMapper;

    /**
     * 学生端提交测评题答案
     * 参数List<AnswerEntity>   实体添加了 studentId  学生id   questionId 题库id   字段
     * 韦良伟
     *
     * @param list
     * @return
     */
    @Override
    @Transactional
    public HttpResponseEntity answerQuestion(List<AnswerEntity> list) {
        try {

            String studentId = list.get(0).getStudentId();    //学生Id
            String questionId = list.get(0).getQuestionId();  //题id
            GradeEvaluationEntity gradeEvaluationEntity = gradeEvaluationEntityMapper.markStudentSeequestionId(questionId);  //根据问题id查评测记录表
            String gradeEvaluationId = gradeEvaluationEntity.getGradeEvaluationId();
            Date date = new Date();
            String uuid = UUIDUtil.getOneUUID();
            list.forEach(answerEntity -> {
                answerEntity.setAnswerId(UUIDUtil.getOneUUID());
                answerEntity.setModifyTime(date);
                answerEntity.setCreateTime(date);
                answerEntity.setStatus(1);
                answerEntity.setMarkId(uuid);
            });
            int i = answerEntityMapper.answerQuestion(list);                                        //插入答案
            MarkEntity markEntity = new MarkEntity();
            markEntity.setMarkId(uuid);
            markEntity.setCreateTime(date);
            markEntity.setModifyTime(date);
            markEntity.setStudentId(studentId);
            markEntity.setGradeEvaluationId(gradeEvaluationId);
            markEntity.setStatus(1);
            int t = markEntityMapper.insertSelective(markEntity);

            /**
             * 学徒制三期，添加分值修改为百分制
             * by 王传营 2020-03-26
             */

            //查询用到的维度，分别有多少题
            ProblemEntity problemEntity = new ProblemEntity();
            problemEntity.setQuestionBankId(gradeEvaluationEntity.getQuestionId());
            List<ProblemEntity> problemEntitys = problemEntityMapper.selectDimension(problemEntity);

            SolutionEntity solutionEntity = new SolutionEntity();
            solutionEntity.setProblemId(problemEntitys.get(0).getProblemId());
            List<SolutionEntity> solutionEntities = solutionEntityMapper.selectFraction(solutionEntity);

            List<DimensionRecordEntity> list1 = dimensionRecordEntityMapper.selectSum(list);         //对应维度算和
            for (int s = 0 ; s < problemEntitys.size(); s++){
                for (int z = 0; z < list1.size(); z++){
                    if (problemEntitys.get(s).getProblemDimension().equals(list1.get(z).getDimensionId())){
                        int sum = problemEntitys.get(s).getSum();
                        double dimensionRecordNum = list1.get(z).getDimensionRecordNum();
                        double solutionFraction = solutionEntities.get(0).getSolutionFraction();
                        double i1 = (100 / (sum * solutionFraction)) * dimensionRecordNum;
                        list1.get(z).setDimensionRecordNum((int) i1);
                    }
                }
            }
            list1.forEach(dimensionRecordEntity -> {
                        dimensionRecordEntity.setDimensionRecordId(UUIDUtil.getOneUUID());
                        dimensionRecordEntity.setCreateTime(date);
                        dimensionRecordEntity.setModifyTime(date);
                        dimensionRecordEntity.setMarkId(uuid);
                        dimensionRecordEntity.setStatus(1);
                    }
            );
            int z = dimensionRecordEntityMapper.insertSum(list1);                                 //添加到总记录分数表

            if (i > 0 && t > 0 && z > 0) {
                StudentEntity studentEntity = new StudentEntity();                               //提交成功后修改学生的留级字段为0
                studentEntity.setStudentId(studentId);
                studentEntity.setRetardation(0);
                int m = studentEntityMapper.updateByPrimaryKeySelective(studentEntity);
                if (m >= 0) {
                    logger.info("答案提交成功，接口{}", "answerQuestion");
                    return HttpResponseEntity.seccuss("答案提交成功");
                }
                logger.info("答案提交失败，接口{}", "answerQuestion");
                return HttpResponseEntity.error("答案提交失败");
            } else {
                logger.info("答案提交失败，接口{}", "answerQuestion");
                return HttpResponseEntity.error("答案提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("答案提交失败，接口{}", "answerQuestion");
            return HttpResponseEntity.error("答案提交失败");
        }
    }
}

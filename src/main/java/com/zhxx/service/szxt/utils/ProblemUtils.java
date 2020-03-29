package com.zhxx.service.szxt.utils;/**
 * Created by Administrator on 2018/12/8.
 */

import com.alibaba.fastjson.JSON;
import com.zhxx.service.szxt.entity.ProblemEntity;
import com.zhxx.service.szxt.entity.SolutionEntity;
import com.zhxx.service.szxt.entity.TestPaper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProblemUtils
 * @Auther: 王新春
 * @Date: 2018/12/8 16:59
 * @Description:
 */
public class ProblemUtils {

    public static List<ProblemEntity> getProblem(List<TestPaper> testPapers, String questionBankId) {

        List<ProblemEntity> problemEntityList = new ArrayList<>();
        for (TestPaper testPaper : testPapers) {
            ProblemEntity problemEntity = new ProblemEntity();
            String problemId = UUIDUtil.getOneUUID();
            problemEntity.setProblemId(problemId);

            problemEntity.setCreateTime(new Date());
            problemEntity.setModifyTime(new Date());
            problemEntity.setStatus(1);
            problemEntity.setProblemTitle(testPaper.getTitle());
            problemEntity.setProblemDimension(testPaper.getDimensionNum());
            problemEntity.setQuestionBankId(questionBankId);//题库id
            List<SolutionEntity> solutionEntityList = new ArrayList<>();
            if (testPaper.getAnswer1() != null) {
                solutionEntityList.add(getSolution(testPaper.getAnswer1(), testPaper.getAnswerNum1(), problemId));
            }
            if (testPaper.getAnswer2() != null) {
                solutionEntityList.add(getSolution(testPaper.getAnswer2(), testPaper.getAnswerNum2(), problemId));
            }
            if (testPaper.getAnswer3() != null) {
                solutionEntityList.add(getSolution(testPaper.getAnswer3(), testPaper.getAnswerNum3(), problemId));
            }
            if (testPaper.getAnswer4() != null) {
                solutionEntityList.add(getSolution(testPaper.getAnswer4(), testPaper.getAnswerNum4(), problemId));
            }
            problemEntity.setSolutionEntities(solutionEntityList);
            problemEntityList.add(problemEntity);
        }
        return problemEntityList;
    }

    public static SolutionEntity getSolution(String name, Integer num, String problemId) {
        SolutionEntity solutionEntity = new SolutionEntity();
        solutionEntity.setCreateTime(new Date());
        solutionEntity.setModifyTime(new Date());
        solutionEntity.setProblemId(problemId);
        solutionEntity.setStatus(1);
        solutionEntity.setSolutionContent(name);
        solutionEntity.setSolutionFraction(num);
        solutionEntity.setSolutionId(UUIDUtil.getOneUUID());
        return solutionEntity;
    }
}

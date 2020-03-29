package com.zhxx.service.szxt.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.ProblemEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;
import com.zhxx.service.szxt.entity.TestPaper;
import com.zhxx.service.szxt.mapper.ProblemEntityMapper;
import com.zhxx.service.szxt.mapper.QuestionBankEntityMapper;
import com.zhxx.service.szxt.mapper.SolutionEntityMapper;
import com.zhxx.service.szxt.service.QuestionBankService;

import com.zhxx.service.szxt.utils.ExcelHelper;
import com.zhxx.service.szxt.utils.ProblemUtils;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by huasheng on 2018/12/5.
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    private final Logger logger = LoggerFactory.getLogger(QuestionBankService.class);
    @Autowired
    private RemoteProperties remoteProperties;
    @Autowired
    private QuestionBankEntityMapper questionBankEntityMapper;

    @Autowired
    private ProblemEntityMapper problemEntityMapper;

    @Autowired
    private SolutionEntityMapper solutionEntityMapper;

    /**
     * 题库查询
     *
     * @param map
     * @return
     */
    @Override
    public HttpResponseEntity selectQuestionBank(Map<String, String> map) {
        try {
            QuestionBankEntity questionBankEntity = new QuestionBankEntity();
            if (!StringUtils.isEmpty(map.get("questionBankName"))) {
                questionBankEntity.setQuestionBankName(map.get("questionBankName"));
            }
            if (!StringUtils.isEmpty(map.get("userName"))) {
                questionBankEntity.setUserName(map.get("userName"));
            }
            if (!StringUtils.isEmpty(map.get("questionBankStage"))) {
                questionBankEntity.setQuestionBankStage(Integer.parseInt(map.get("questionBankStage")));
            }
            //获取页数
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            //获取条数
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            //判断规格
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            //分页插件
            PageHelper.startPage(pageNum, pageSize);

            List<QuestionBankEntity> list = questionBankEntityMapper.selectQuestionBank(questionBankEntity);

//           list.forEach(questionBankEntity1 -> questionBankEntity1.setQuestionBankFilePath(questionBankEntity1.getQuestionBankFilePath()));

            PageInfo<QuestionBankEntity> pageInfo = new PageInfo<>(list);
            logger.info("题库查询成功，接口{}", "selectQuestionBank");
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            logger.info("题库查询失败，接口{}", "selectQuestionBank");
            return HttpResponseEntity.error("请求失败");
        }
    }


    /**
     * 试卷预览
     *
     * @param questionBankEntity
     * @return
     */
    @Override
    public HttpResponseEntity problemAndSolution(QuestionBankEntity questionBankEntity) {
        try {
            List<ProblemEntity> list = questionBankEntityMapper.problemAndSolution(questionBankEntity.getQuestionBankId());

            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            logger.info("预览失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 添加接口
     *
     * @param questionBankEntity
     * @return
     */
    @Override
    @Transactional
    public HttpResponseEntity addQuestionBank(QuestionBankEntity questionBankEntity) {
        try {
            questionBankEntity.setQuestionBankId(UUIDUtil.getOneUUID());
            Date date = new Date();
            questionBankEntity.setCreateTime(date);
            questionBankEntity.setModifyTime(date);
            questionBankEntity.setStatus(1);
            File excel = new File(Const.ConstInter.PATH_URL + questionBankEntity.getQuestionBankFilePath());
            List<TestPaper> testPapers = ExcelHelper.analysisExcel(excel, TestPaper.class);
            if(testPapers == null || testPapers.size() <=0){
                return HttpResponseEntity.error("文件格式不正确");
            }
            List<ProblemEntity> problemEntityList = ProblemUtils.getProblem(testPapers, questionBankEntity.getQuestionBankId());

            int num = problemEntityMapper.insertList(problemEntityList);
            problemEntityList.forEach(item -> {
                solutionEntityMapper.insertList(item.getSolutionEntities());
            });

            int i = questionBankEntityMapper.addQuestionBank(questionBankEntity);

            if (i > 0) {
                logger.info("题库添加成功，接口{}", "addQuestionBank");
                return HttpResponseEntity.seccuss("请求成功");
            } else {
                logger.info("题库添加失败，接口{}", "addQuestionBank");
                return HttpResponseEntity.error("请求失败");
            }
        } catch (Exception e) {
            logger.info("题库添加失败，接口{}", "addQuestionBank");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 试卷删除
     *
     * @param questionBankEntity
     * @return
     */
    @Override
    public HttpResponseEntity deleteQuestionBank(QuestionBankEntity questionBankEntity) {
        try {
//            String questionBankId = questionBankEntity.getQuestionBankId();
            int res = questionBankEntityMapper.updateQuestionStatus(questionBankEntity);
            if (res == 1) {
                return HttpResponseEntity.seccuss("试卷删除成功");
            } else {
                return HttpResponseEntity.error("试卷删除失败");
            }
        } catch (Exception e) {
            return HttpResponseEntity.error("请求失败");
        }
    }
}

package com.zhxx.service.szxt.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.GradeEvaluationEntity;
import com.zhxx.service.szxt.entity.MarkEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.mapper.DimensionEntityMapper;
import com.zhxx.service.szxt.mapper.GradeEvaluationEntityMapper;
import com.zhxx.service.szxt.mapper.MarkEntityMapper;
import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.service.MarkService;
import com.zhxx.service.szxt.service.QuestionBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huasheng on 2018/12/6.
 */
@Service
public class MarkServiceImpl implements MarkService {
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);
    @Autowired
    private MarkEntityMapper markEntityMapper;

    @Autowired
    private GradeEvaluationEntityMapper gradeEvaluationEntityMapper;

    @Autowired
    private DimensionEntityMapper dimensionEntityMapper;
   
    @Autowired
    private RemoteProperties remoteProperties;
    /**
     * 学生评测查询接口
     *
     * @param markEntity
     * @return
     */
    public HttpResponseEntity selectMarkComplete(MarkEntity markEntity) {

        try {
            List<GradeEvaluationEntity> list = markEntityMapper.selectMarkComplete(markEntity.getStudentId());
            logger.info("评测试题查询成功，接口{}", "problemAndSolutionStudent");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("试题查询失败，接口{}", "problemAndSolutionStudent");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 未点评学生列表查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    public HttpResponseEntity selectNotMark(Map<String, Object> map) {
        try {
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);

            String majorId = map.get("majorId").toString();
            String studentNo = map.get("studentNo").toString();
            String Grades = map.get("Grade").toString();
            String evaluationName = map.get("evaluationName").toString();
            String studentName = map.get("studentName").toString();
            Map<String, Object> hsmap = new HashMap<>();
            if (!majorId.isEmpty()) {
                hsmap.put("majorId", majorId);
            }
            if (!studentNo.isEmpty()) {
                hsmap.put("studentNo", studentNo);
            }
            if (!Grades.isEmpty()) {
                int Grade = Integer.parseInt(Grades);
                hsmap.put("Grade", Grade);
            }
            if (!evaluationName.isEmpty()) {
                hsmap.put("evaluationName", evaluationName);
            }
            if (!studentName.isEmpty()) {
                hsmap.put("studentName", studentName);
            }
            List<MarkEntity> list = markEntityMapper.searchNotMark(hsmap);
            PageInfo pageInfo = new PageInfo(list);

            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 学生列表中学生详细信息查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    public HttpResponseEntity selectNotMarkComment(Map<String, Object> map) {
        try {
            String gradeEvaluationId = map.get("gradeEvaluationId").toString();
            String studentId = map.get("studentId").toString();
            String markId = map.get("markId").toString();
            Map<String, Object> hsmap = new HashMap<>();
            hsmap.put("gradeEvaluationId", gradeEvaluationId);
            hsmap.put("studentId", studentId);
            hsmap.put("markId", markId);

            List<MarkEntity> list = markEntityMapper.selectNotMarkComment(hsmap);
            list.get(0).getStudentEntity().get(0).setHeadImgPath(remoteProperties.getHttpUrl() + 
            		list.get(0).getStudentEntity().get(0).getHeadImg());
           
            List<MarkEntity> newList = gradeEvaluationEntityMapper.selectQuestionAnswer(gradeEvaluationId);

            List<MarkEntity> dimension_name = markEntityMapper.selectName(markId);
            list.addAll(newList);
            list.addAll(dimension_name);
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }

    }

    /**
     * 已点评学生列表查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    public HttpResponseEntity selectMark(Map<String, Object> map) {
        try {
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);

            String majorId = map.get("majorId").toString();
            String studentNo = map.get("studentNo").toString();
            String Grades = map.get("Grade").toString();
            String evaluationName = map.get("evaluationName").toString();
            String studentName = map.get("studentName").toString();
            Map<String, Object> hsmap = new HashMap<>();
            if (!majorId.isEmpty()) {
                hsmap.put("majorId", majorId);
            }
            if (!studentNo.isEmpty()) {
                hsmap.put("studentNo", studentNo);
            }
            if (!Grades.isEmpty()) {
                int Grade = Integer.parseInt(Grades);
                hsmap.put("Grade", Grade);
            }
            if (!evaluationName.isEmpty()) {
                hsmap.put("evaluationName", evaluationName);
            }
            if (!studentName.isEmpty()) {
                hsmap.put("studentName", studentName);
            }
            List<MarkEntity> list = markEntityMapper.searchMark(hsmap);
            PageInfo pageInfo = new PageInfo(list);

            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 已点评学生列表查询成绩
     * by 王传营  2020-01-6
     * @param map
     */
    @Override
    public HttpResponseEntity selectMarkGrade(Map<String, Object> map) {
        try {
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);

            String majorId = map.get("majorId").toString();
            String studentNo = map.get("studentNo").toString();
            String Grades = map.get("startSchool").toString();
        //    String evaluationName = map.get("evaluationName").toString();
            String studentName = map.get("studentName").toString();
            Map<String, Object> hsmap = new HashMap<>();
            if (!majorId.isEmpty()) {
                hsmap.put("majorId", majorId);
            }
            if (!studentNo.isEmpty()) {
                hsmap.put("studentNo", studentNo);
            }
            if (!Grades.isEmpty()) {
                int Grade = Integer.parseInt(Grades);
                hsmap.put("Grade", Grade);
            }
//            if (!evaluationName.isEmpty()) {
//                hsmap.put("evaluationName", evaluationName);
//            }
            if (!studentName.isEmpty()) {
                hsmap.put("studentName", studentName);
            }
            List<MarkEntity> list = markEntityMapper.searchMarkGrade(hsmap);
            for (int i = 0; i < list.size(); i++){
                Map<String, Object> map1 = new HashMap<>();
                map1.put("studentName",list.get(i).getStudentEntity().get(0).getStudentName());
                map1.put("studentNo",list.get(i).getStudentEntity().get(0).getStudentNo());
                List<MarkEntity> markEntities = markEntityMapper.searchMarkGrade1(map1);
                list.get(i).setMarklist(markEntities);
            }
            PageInfo pageInfo = new PageInfo(list);

            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 教师提交评测接口
     *
     * @param markEntity
     * @return
     */
    public HttpResponseEntity pushMark(MarkEntity markEntity) {
        try {
            Date date=new Date();
            markEntity.setModifyTime(date);
            int rs = markEntityMapper.pushMark(markEntity);
            if (rs == 1) {
                return HttpResponseEntity.seccuss("提交成功");
            } else {
                return HttpResponseEntity.seccuss("提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }
    }


}

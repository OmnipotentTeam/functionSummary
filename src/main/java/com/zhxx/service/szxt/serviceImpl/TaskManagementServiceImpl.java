package com.zhxx.service.szxt.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.admin.server.dao.RoleDao;
import com.zhxx.admin.server.dao.UserDao;
import com.zhxx.admin.server.model.Role;
import com.zhxx.admin.server.model.User;
import com.zhxx.admin.server.utils.UserUtil;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.StageEntity;
import com.zhxx.service.szxt.entity.StageEntity;
import com.zhxx.service.szxt.entity.StageRecordEntity;
import com.zhxx.service.szxt.entity.TaskEntity;
import com.zhxx.service.szxt.mapper.StageEntityMapper;
import com.zhxx.service.szxt.entity.TaskRecordEntity;

import com.zhxx.service.szxt.entity.TaskRecordEntity;
import com.zhxx.service.szxt.mapper.StageRecordEntityMapper;
import com.zhxx.service.szxt.mapper.TaskEntityMapper;
import com.zhxx.service.szxt.mapper.TaskRecordEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherRoleEntityMapper;
import com.zhxx.service.szxt.service.GradeEvaluationService;
import com.zhxx.service.szxt.service.TaskManagementService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by chen on 2018/12/10.
 */
@Service
public class TaskManagementServiceImpl implements TaskManagementService {
    private final Logger logger = LoggerFactory.getLogger(GradeEvaluationService.class);
    @Autowired
    private RemoteProperties remoteProperties;
    @Autowired
    private TaskEntityMapper taskEntityMapper;
    @Autowired
    private StageEntityMapper stageEntityMapper;
    @Autowired
    private TaskRecordEntityMapper taskRecordEntityMapper;
    @Autowired
    private StageRecordEntityMapper stageRecordEntityMapper;
    @Autowired
    private RoleDao roleDao;

    /**
     * 修改人  王传营
     *
     */

    //查询所有任务
    public HttpResponseEntity selectUnpublishedTask(TaskEntity taskEntity) {
        try {
            if (StringUtils.isEmpty(taskEntity.getTaskName())) {
                taskEntity.setTaskName(null);
            }
            if (StringUtils.isEmpty(taskEntity.getState())) {
                taskEntity.setState("0");
            }
            if (StringUtils.isEmpty(taskEntity.getDimensionId())) {
                taskEntity.setDimensionId(null);
            }
            if (taskEntity.getUserId() == 0) {
                taskEntity.setUserId(null);
            }
            int pageNum = taskEntity.getPageNum();
            int pageSize = taskEntity.getPageSize();
//            taskEntity.setPageNum(null);
//            taskEntity.setPageSize(null);
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);

            List<TaskEntity> list = null;
            User currentUser = UserUtil.getCurrentUser();
            List<Role> roles = roleDao.listByUserId(currentUser.getId());
            String identification = "";
            if ("企业".equals(roles.get(0).getName())){
                taskEntity.setUserId(Math.toIntExact(currentUser.getId()));
                identification = "企业";  //识别身份
//            List<TaskEntity> list = taskEntityMapper.selectUnpublishedTask(taskEntity);  //修改了sql语句  查出全部 不为总结（stageNum!==100）
                list = taskEntityMapper.selectUpTask(taskEntity);      //防止分页错乱  先查询任务列表
            }else {
                identification = "教师";  //识别身份
                list = taskEntityMapper.selectUpTask(taskEntity);      //防止分页错乱  先查询任务列表
            }
            if (list.size() != 0) {
                list.get(0).setIdentification(identification);
            }
            int i = list.size();
            for (int selectstage = 0; selectstage < i; selectstage++) {

                //添加发布方，获取发布角色
                List<Role> roles1 = roleDao.listByUserId(Long.valueOf(list.get(selectstage).getUserId()));
                list.get(selectstage).setPublisher(roles1.get(0).getName());

                String taskId = list.get(selectstage).getTaskId();                 //根据任务查询除了总结的所有阶段
                List<StageEntity> stageEntityList = stageEntityMapper.selectStageEndBefore(taskId);
                list.get(selectstage).setStageEntityList(stageEntityList);            //阶段添加到任务list 中
            }


            PageInfo pageInfo = new PageInfo(list);
//            pageInfo.setTotal(list.size());
//            pageInfo.setNavigatePages(list.size());
            logger.info("未发布任务查询成功，接口{}", "selectUnpublishedTask");
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("未发布任务查询失败，接口{}", "selectUnpublishedTask");
            return HttpResponseEntity.error("请求失败");
        }
    }

    //根据id删除
    public HttpResponseEntity deleteUnpublishedTask(TaskEntity taskEntity) {

        try {
            int a = taskEntityMapper.deleteUnpublishedTask(taskEntity);
            return HttpResponseEntity.seccuss("删除成功");
        } catch (Exception e) {
            logger.info("删除失败，接口{}", "deleteUnpublishedTask");
            return HttpResponseEntity.error("请求失败");
        }
    }

    //发布任务
    public HttpResponseEntity updateUnpublishedTask(TaskEntity taskEntity) {
        try {
            int a = taskEntityMapper.updateUnpublishedTask(taskEntity);
            return HttpResponseEntity.seccuss("修改成功");

        } catch (Exception e) {
            logger.info("修改失败，接口{}", "updateUnpublishedTask");
            return HttpResponseEntity.error("请求失败");
        }
    }

    //添加任务和阶段
    @Transactional
    public HttpResponseEntity insertUnpublishedTask(TaskEntity taskEntity) {
        try {
            Date date = new Date();
            String taskId = UUIDUtil.getOneUUID();
            taskEntity.setTaskId(taskId);
            taskEntity.setCreateTime(date);
            taskEntity.setModifyTime(date);
            taskEntity.setTaskStatus(0);
            taskEntity.setStatus(1);
            int a = taskEntityMapper.insert(taskEntity);
            if (taskEntity.getStageEntityList() != null && taskEntity.getStageEntityList().size() > 0) {
                for (StageEntity stageEntity : taskEntity.getStageEntityList()) {
                    if (stageEntity.getStageContent() == null) {
                        System.out.println("这是一个空阶段，错误数据");                                         //前端传送数据有问题  进行过滤  把阶段内容为空的过滤掉
                    } else {
                        stageEntity.setStageId(UUIDUtil.getOneUUID());
                        stageEntity.setTaskId(taskId);
                        stageEntity.setCreateTime(date);
                        stageEntity.setModifyTime(date);
                        stageEntity.setStatus(1);
                        int b = stageEntityMapper.insert(stageEntity);
                    }

                }

                StageEntity stageEntity = new StageEntity();
                stageEntity.setStageId(UUIDUtil.getOneUUID());
                stageEntity.setStageNum(100);                      //添加总结阶段  设置为100
                stageEntity.setStageContent("任务总结");
                stageEntity.setTaskId(taskId);
                stageEntity.setCreateTime(date);
                stageEntity.setModifyTime(date);
                stageEntity.setStatus(1);
                stageEntity.setStageStopTime(taskEntity.getTaskStopTime());  //总结的截止时间设置成任务截止时间
                int m = stageEntityMapper.insert(stageEntity);
            }
            logger.info("添加成功，接口{}", "insertUnpublishedTask");
            return HttpResponseEntity.seccuss("请求成功");

        } catch (Exception e) {
            logger.info("添加失败，接口{}", "insertUnpublishedTask");
            return HttpResponseEntity.error("请求失败");
        }
    }

    //编辑任务和阶段
    @Transactional
    public HttpResponseEntity updateUnpublishedTasks(TaskEntity taskEntity) {
        try {
            Date date = new Date();
            String taskId = taskEntity.getTaskId();
            taskEntity.setCreateTime(date);
            taskEntity.setModifyTime(date);
            taskEntity.setTaskStatus(0);
            taskEntity.setStatus(1);
            int a = taskEntityMapper.updateByPrimaryKey(taskEntity);
            if (taskEntity.getStageEntityList() != null && taskEntity.getStageEntityList().size() > 0) {
                int c = stageEntityMapper.deleteByTaskId(taskId);
                if (c > 0) {
                    for (StageEntity stageEntity : taskEntity.getStageEntityList()) {
                        stageEntity.setTaskId(taskId);
                        stageEntity.setCreateTime(date);
                        stageEntity.setModifyTime(date);
                        stageEntity.setStageId(UUIDUtil.getOneUUID());
                        stageEntity.setStatus(1);
                        int d = stageEntityMapper.insert(stageEntity);
                    }
                    StageEntity stageEntity = new StageEntity();
                    stageEntity.setStageId(UUIDUtil.getOneUUID());
                    stageEntity.setStageNum(100);                      //添加总结阶段  设置为100
                    stageEntity.setStageContent("任务总结");
                    stageEntity.setTaskId(taskId);
                    stageEntity.setCreateTime(date);
                    stageEntity.setModifyTime(date);
                    stageEntity.setStatus(1);
                    stageEntity.setStageStopTime(taskEntity.getTaskStopTime());  //总结的截止时间设置成任务截止时间
                    int m = stageEntityMapper.insert(stageEntity);
                }
            }
            logger.info("添加失败，接口{}", "insertUnpublishedTask");
            return HttpResponseEntity.seccuss("请求成功");

        } catch (Exception e) {
            logger.info("添加失败，接口{}", "insertUnpublishedTask");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 查询未点评任务详情,已点评任务详情
     *
     * @param map
     * @return
     */
    public HttpResponseEntity selectTaskStudentStage(Map<String, String> map) {
        try {
            if (!StringUtils.isEmpty(map.get("studentId")) && !StringUtils.isEmpty(map.get("taskId")) && !StringUtils.isEmpty(map.get("taskRecordStatus"))) {
                String studentId = map.get("studentId");      //获取学生id
                String taskId = map.get("taskId");            //获取任务id
                int taskRecordStatus = Integer.parseInt(map.get("taskRecordStatus"));    //获取任务状态

                TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                taskRecordEntity.setStudentId(studentId);
                taskRecordEntity.setTaskId(taskId);
                taskRecordEntity.setTaskRecordStatus(taskRecordStatus);
                TaskRecordEntity taskRecordEntity1 = taskRecordEntityMapper.selectOneTaskRecord(taskRecordEntity);  //获取任务记录表
                String taskRecordId = taskRecordEntity1.getTaskRecordId();   //获取任务记录id
                List<StageEntity> stageEntityList = stageRecordEntityMapper.selectStageRecordAll(taskRecordId); //对应任务信息
                stageEntityList.forEach(stageEntity -> stageEntity.getStageRecord().forEach(stageRecordEntity -> stageRecordEntity.setStageRecordImgPath(remoteProperties.getHttpUrl() + stageRecordEntity.getStageRecordImgPath())));  //添加服务器路径
                TaskEntity taskEntity = taskEntityMapper.selectPcTask(taskId);
                Map<String, Object> map1 = new HashedMap();
                map1.put("TaskEntity", taskEntity);
                map1.put("StageEntity", stageEntityList);
                map1.put("TaskRecordEntity",taskRecordEntity1);
                logger.info("查询任务详情成功，接口{}", "selectTaskStudentStage");
                return HttpResponseEntity.seccuss(map1);
            }
            logger.info("查询任务详情失败，接口{}", "selectTaskStudentStage");
            return HttpResponseEntity.seccuss("查询任务详情失败");
        } catch (Exception e) {
            logger.info("查询任务详情失败，接口{}", "selectTaskStudentStage");
            return HttpResponseEntity.error("查询任务详情失败");
        }
    }


    /**
     * 教师点评任务接口
     *
     * @param map
     * @return
     */
    @Transactional
    public HttpResponseEntity commentStage(Map<String, String> map) {
        try {
            StageRecordEntity stageRecordEntity = new StageRecordEntity();
            if (!StringUtils.isEmpty(map.get("stageRecordId")) && !StringUtils.isEmpty(map.get("taskRecordId")) && !StringUtils.isEmpty(map.get("stageRecordEvaluate")) && !StringUtils.isEmpty(map.get("stageRecordStatus")) && !StringUtils.isEmpty(map.get("end"))) {
                String stageRecordId = map.get("stageRecordId");   //获取阶段记录id
                String taskRecordId = map.get("taskRecordId");     //获取任务记录id
                int end = Integer.parseInt(map.get("end"));
                String stageRecordEvaluate = map.get("stageRecordEvaluate"); //获取教师点评内容
                int stageRecordStatus = Integer.parseInt(map.get("stageRecordStatus"));  //获取阶段点评状态
                stageRecordEntity.setStageRecordId(stageRecordId);
//               stageRecordEntity.setTaskRecordId(taskRecordId);
                stageRecordEntity.setStageRecordStatus(stageRecordStatus);
                stageRecordEntity.setStageRecordEvaluate(stageRecordEvaluate);
                Date date = new Date();
                stageRecordEntity.setModifyTime(date);
                int i = stageRecordEntityMapper.updateByPrimaryKeySelective(stageRecordEntity);
                TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                taskRecordEntity.setTaskRecordId(taskRecordId);
                taskRecordEntity.setModifyTime(date);
                int z = 0;
                if (end == 0) {
                    taskRecordEntity.setTaskRecordStatus(1);
                    z = taskRecordEntityMapper.updateByPrimaryKeySelective(taskRecordEntity);
                } else if (end == 1) {
                    if (stageRecordStatus == 1||stageRecordStatus == 3||stageRecordStatus == 4||stageRecordStatus == 5) {
                        taskRecordEntity.setTaskRecordStatus(2);

                    } else if (stageRecordStatus == -1) {
                        taskRecordEntity.setTaskRecordStatus(1);
                    }
                    z = taskRecordEntityMapper.updateByPrimaryKeySelective(taskRecordEntity);
                }
                if (i > 0 && z > 0) {
                    logger.info("点评任务成功，接口{}", "commentStage");
                    return HttpResponseEntity.seccuss("点评任务成功");
                } else {
                    logger.info("点评任务失败，接口{}", "commentStage");
                    return HttpResponseEntity.error("点评任务失败");
                }
            }
            logger.info("点评任务失败，接口{}", "commentStage");
            return HttpResponseEntity.error("点评任务失败");

        } catch (Exception e) {
            logger.info("点评任务失败，接口{}", "commentStage");
            return HttpResponseEntity.error("点评任务失败");
        }
    }

    /**
     * 任务查询,任务点评接口
     *
     * @param map
     * @return
     */
    public HttpResponseEntity selectTaskAll(Map<String, Object> map) {
        try {
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());

            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            String majorName = map.get("majorName").toString();
            String taskName = map.get("taskName").toString();
            String studentName = map.get("studentName").toString();
            String studentNo = map.get("studentNo").toString();
            String dimensionName = map.get("dimensionName").toString();
            Map<String, Object> hashMap = new HashMap<>();

            if (!majorName.isEmpty()) {
                hashMap.put("majorName", majorName);
            } else {
                hashMap.put("majorName", null);
            }
            if (!taskName.isEmpty()) {
                hashMap.put("taskName", taskName);
            } else {
                hashMap.put("taskName", null);
            }
            if (!studentName.isEmpty()) {
                hashMap.put("studentName", studentName);
            } else {
                hashMap.put("studentName", null);
            }
            if (!studentNo.isEmpty()) {
                hashMap.put("studentNo", studentNo);
            } else {
                hashMap.put("studentNo", null);
            }
            if (!dimensionName.isEmpty()) {
                hashMap.put("dimensionName", dimensionName);
            } else {
                hashMap.put("dimensionName", null);
            }
            if (map.get("studentGrade") == null) {
                hashMap.put("studentGrade", null);
            } else {
                if (!StringUtils.isEmpty(map.get("studentGrade").toString())) {
                    hashMap.put("studentGrade", map.get("studentGrade").toString());
                } else {
                    hashMap.put("studentGrade", null);
                }

            }

            if (!StringUtils.isEmpty(map.get("taskRecordStatus").toString())) {
                hashMap.put("taskRecordStatus", map.get("taskRecordStatus").toString());
            }
            List<TaskRecordEntity> list = taskRecordEntityMapper.selectTaskAll(hashMap);
            if (list.size() == 0) {
//                return HttpResponseEntity.error("请求失败");
                PageInfo pageInfo = new PageInfo(list);
                return HttpResponseEntity.seccuss(pageInfo);
            }
            int listnumber = list.size();
            for (int selectnum = 0; selectnum < listnumber; selectnum++) {
                String taskRecordId = list.get(selectnum).getTaskRecordId();
                List<StageEntity> stageEntityList = stageRecordEntityMapper.selectStageRecordAll(taskRecordId);
                int Num = stageEntityList.get(stageEntityList.size() - 1).getStageNum();
                list.get(selectnum).getTaskEntities().get(0).getStageEntities().get(0).setStageNum(Num);          //把学生最后评测的阶段放入list中
            }


            PageInfo pageInfo = new PageInfo(list);
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponseEntity.error("请求失败");
        }
    }
}

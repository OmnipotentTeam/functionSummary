package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.TaskEntity;
import com.zhxx.service.szxt.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by chen on 2018/12/10.
 */
@Controller
@RequestMapping("/task")
public class TaskManagementController {
    @Autowired
    private TaskManagementService taskManagementService;

    /**
     * 查询所有任务接口
     * 传入参数pageNum:int,
     * pageSize: int,
     * dimensionId:String,
     * taskName:String,
     * userId:int,
     * state:String(0,1,2)
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/selectUnpublishedTask", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectUnpublishedTask(@RequestBody TaskEntity taskEntity) {
        return taskManagementService.selectUnpublishedTask(taskEntity);
    }

    /**
     * 通过taskId删除任务和阶段
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/deleteUnpublishedTask", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity deleteUnpublishedTask(@RequestBody TaskEntity taskEntity) {
        return taskManagementService.deleteUnpublishedTask(taskEntity);
    }

    /**
     * 通过taskId发布任务
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/updateUnpublishedTask", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateUnpublishedTask(@RequestBody TaskEntity taskEntity) {
        return taskManagementService.updateUnpublishedTask(taskEntity);
    }

    /**
     * 添加任务和阶段接口
     * 传如参数：taskName:String,
     * dimensionId:String,
     * taskStopTime:Date,
     * userId:int,
     * stageEntityList:list
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/insertUnpublishedTask", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity insertUnpublishedTask(@RequestBody TaskEntity taskEntity) {
        return taskManagementService.insertUnpublishedTask(taskEntity);
    }


    /**
     * 编辑任务和阶段接口
     * 传如参数：taskId:String,
     * taskName:String,
     * dimensionId:String,
     * taskStopTime:Date,
     * userId:int,
     * stageEntityList:list
     *
     * @param taskEntity
     * @return
     */
    @RequestMapping(value = "/updateUnpublishedTasks", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateUnpublishedTasks(@RequestBody TaskEntity taskEntity) {
        return taskManagementService.updateUnpublishedTasks(taskEntity);
    }


    /**
     * 教师端 查询未点评任务接口，已点评任务接口
     * 参数Map   studentId（学生id）  taskId（任务id）
     * taskRecordStatus（任务记录状态 0  未点评  ， 1   已点评，-1   已超时，2  已完成，3 已接受）
     * 韦良伟
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectTaskStudentStage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectTaskStudentStage(@RequestBody Map<String, String> map) {
        return taskManagementService.selectTaskStudentStage(map);
    }

    /**
     * 教师端 点评任务
     * 参数Map  stageRecordId（阶段记录id）  taskRecordId（任务记录id） stageRecordEvaluate（教师点评）
     * stageRecordStatus（是否通过 -1 没通过 ，1  通过  ，2 没点评）
     * end(新添加个参数  表示是否是总结   0 表示不是，1 表示是)
     * 韦良伟
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/commentStage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity commentStage(@RequestBody Map<String, String> map) {
        return taskManagementService.commentStage(map);
    }

    /**
     * 任务查询,任务点评接口
     * 参数Map  majorName(专业)，studentGrade(学年),taskName(任务名称),studentName(姓名),studentNo(学号)
     * taskRecordStatus(任务记录状态 0  未点评  ， 1   已点评，-1   已超时，2  已完成，3 已接受),dimensionName(维度)
     * 陈奇
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectTaskAll", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectTaskAll(@RequestBody Map<String, Object> map) {
        return taskManagementService.selectTaskAll(map);
    }

}


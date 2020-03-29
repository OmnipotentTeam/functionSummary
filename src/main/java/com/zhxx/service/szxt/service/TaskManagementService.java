package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StageRecordEntity;
import com.zhxx.service.szxt.entity.TaskEntity;

import java.util.Map;

/**
 * Created by chen on 2018/12/10.
 */
public interface TaskManagementService {
    HttpResponseEntity selectUnpublishedTask(TaskEntity taskEntity);

    HttpResponseEntity deleteUnpublishedTask(TaskEntity taskEntity);

    HttpResponseEntity updateUnpublishedTask(TaskEntity taskEntity);

    HttpResponseEntity insertUnpublishedTask(TaskEntity taskEntity);

    HttpResponseEntity updateUnpublishedTasks(TaskEntity taskEntity);

    HttpResponseEntity selectTaskStudentStage(Map<String, String> map);

    HttpResponseEntity commentStage(Map<String, String> map);

    HttpResponseEntity selectTaskAll(Map<String, Object> map);
}


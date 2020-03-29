package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StudentChangeEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.entity.TaskRecordEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/6.
 */
public interface StudentService {
    public HttpResponseEntity addStudent(StudentEntity studentEntity);

    public HttpResponseEntity selectStudent(StudentEntity studentEntity);

    /**
     * by 王传营
     */
    public HttpResponseEntity insertStudentBindingCode(StudentEntity studentEntity);

    public HttpResponseEntity updateStudentInformation(StudentEntity studentEntity);

    public HttpResponseEntity markStudentSee(Map<String, String> map);

    public HttpResponseEntity initialization(StudentEntity studentEntity);

    public HttpResponseEntity selectTaskList(StudentEntity studentEntity);

    public HttpResponseEntity addTaskRecord(TaskRecordEntity taskRecordEntity);

    public HttpResponseEntity taskDetails(StudentEntity studentEntity);

    public HttpResponseEntity submitTask(Map<String, String> map);

    public HttpResponseEntity selectMajorAll();


    public HttpResponseEntity selectStudentAll(Map<String, String> map);    //pc端查询全部学生与搜索

    public HttpResponseEntity deleteStudent(String id);

    public HttpResponseEntity selectStudentChange(StudentEntity studentEntity);

    public HttpResponseEntity updateStudent(Map<String, String> map);

    public HttpResponseEntity selectStudentGrow(StudentEntity studentEntity);


}

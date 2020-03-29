package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/10.
 */
@Controller
@RequestMapping("/information")
public class InformationController {


    @Autowired
    private StudentService studentService;

    /**
     * 删除学生所有相关信息
     * by 王传营 2020-01-14
     * @param id
     * @return
     */
    @RequestMapping ("/deleteStudent/{id}")
    @ResponseBody
    public HttpResponseEntity deleteStudent (@PathVariable("id") String id){
        return studentService.deleteStudent(id);
    }

    /**
     * pc端查询全部学生与搜索
     * 参数   pageSize （分页 ） pageNum（条数） majorId（专业id） studentGrade（学生所在学年）studentPhoneNumber（手机号）
     * studentName（学生姓名） studentNo（学号）
     * 韦良伟
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectStudentAll", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectStudentAll(@RequestBody Map<String, String> map) {
        return studentService.selectStudentAll(map);
    }

    /**
     * pc端学生管理查看学生信息接口(修改按钮)
     * 参数   studentId（学生id）
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/selectStudentChange", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectStudentChange(@RequestBody StudentEntity studentEntity) {
        return studentService.selectStudentChange(studentEntity);
    }

    /**
     * pc端学生修改学生信息接口
     * 参数  StudentEntity 实体（id + 学年+ 专业id等） + userId（教师id）studentChangeDescribe（变更说明）
     * 韦良伟
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateStudent(@RequestBody Map<String, String> map) {
        return studentService.updateStudent(map);
    }

    /**
     * pc端与学生端查询学生成长轨迹
     * StudentEntity   studentId(学生id)
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/selectStudentGrow", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectStudentGrow(@RequestBody StudentEntity studentEntity) {
        return studentService.selectStudentGrow(studentEntity);
    }

}

package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.entity.TaskRecordEntity;
import com.zhxx.service.szxt.service.StudentService;
import com.zhxx.service.szxt.utils.wx.OpenIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/6.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 学生添加接口
     * 参数  StudentEntity  实体对象
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.addStudent(studentEntity);
    }

    /**
     * 学生个人信息查询
     * 参数学生id    studentId(学生id)
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/selectStudent", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.selectStudent(studentEntity);
    }

    /**
     * 学生生成6位邀请码
     * 王传营
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/insertStudentBindingCode", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity insertStudentBindingCode(@RequestBody StudentEntity studentEntity) {
        return studentService.insertStudentBindingCode(studentEntity);
    }

    /**
     * 学生修改个人信息
     * 参数学生的实体对象 StudentEntity
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/updateStudentInformation", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateStudentInformation(@RequestBody StudentEntity studentEntity) {
        return studentService.updateStudentInformation(studentEntity);
    }

    /**
     * 学生端学生查询评测点评内容
     * 需要参数Map包括 studentId(学生id)
     * 韦良伟
     *
     * @param Map
     * @return 返回内容MajorEntity对象
     */
    @RequestMapping(value = "/markStudentSee", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity markStudentSee(@RequestBody Map<String, String> map) {
        return studentService.markStudentSee(map);
    }

    /**
     * 学生端学生初始化方法与刷新方法
     * 传入微信的openid      studentId(学生id)
     * 韦良伟
     *
     * @param StudentEntity
     * @return
     */
    @RequestMapping(value = "/initialization", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity initialization(@RequestBody StudentEntity studentEntity) {
        return studentService.initialization(studentEntity);
    }

    /**
     * 学生加载推荐维度的任务列表
     * 需要学生的id  studentId(学生id)
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */
    @RequestMapping(value = "/selectTaskList", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectTaskList(@RequestBody StudentEntity studentEntity) {
        return studentService.selectTaskList(studentEntity);
    }

    /**
     * 学生选择任务接口
     * 需要askRecordEntity对象其中的对应参数有   studentId(学生id)    taskId  （选择任务的id） markId ( 评测记录的id)
     * 韦良伟
     *
     * @param taskRecordEntity
     * @return
     */
    @RequestMapping(value = "/addTaskRecord", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addTaskRecord(@RequestBody TaskRecordEntity taskRecordEntity) {
        return studentService.addTaskRecord(taskRecordEntity);
    }

    /**
     * 学生查看任务详情接口
     * 需要参数学生id  studentId(学生id)
     * 韦良伟
     *
     * @param studentEntity
     * @return
     */

    @RequestMapping(value = "/TaskDetails", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity taskDetails(@RequestBody StudentEntity studentEntity) {
        return studentService.taskDetails(studentEntity);
    }

    /**
     * 学生提交阶段任务接口
     * 需要Map    studentId(学生id)  stageRecordImgPath（图片地址） stageRecordContent（学生阶段内容） stageId（阶段id）
     * 韦良伟
     *
     * @param Map
     * @return
     */

    @RequestMapping(value = "/submitTask", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity submitTask(@RequestBody Map<String, String> map) {
        return studentService.submitTask(map);
    }

    /**
     * 学生查询全部专业
     * 不需要参数
     * 韦良伟
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectMajorAll", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMajorAll() {
        return studentService.selectMajorAll();
    }

    /**
     * 说明：获取openid
     *
     * @author： 王新春
     * @exception：
     * @date： 2018/12/15 11:39
     */
    @RequestMapping(value = "/getOpenId", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity getOpenId(@RequestBody String code) {
        //获取openid
        String openId = OpenIdUtils.getOpenId(code);
        return HttpResponseEntity.seccuss(openId);
    }

}

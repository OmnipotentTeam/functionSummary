package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.MarkEntity;
import com.zhxx.service.szxt.service.MarkService;
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
@RequestMapping("/mark")
public class MarkController {
    @Autowired
    private MarkService markService;


    /**
     * 学生查询所有测评的接口列表（时间倒叙）
     * 参数学生的id  studentId
     * 韦良伟
     *
     * @param markEntity
     * @return
     */
    @RequestMapping(value = "/selectMarkComplete", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMarkComplete(@RequestBody MarkEntity markEntity) {
        return markService.selectMarkComplete(markEntity);
    }

    /**
     * 未点评学生列表查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    @RequestMapping(value = "/selectNotMark", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectNotMark(@RequestBody Map<String, Object> map) {
        return markService.selectNotMark(map);
    }

    /**
     * 未点评学生列表中学生详细信息查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    @RequestMapping(value = "/selectNotMarkComment", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectNotMarkComment(@RequestBody Map<String, Object> map) {
        return markService.selectNotMarkComment(map);
    }


    /**
     * 已点评学生列表查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    @RequestMapping(value = "/selectMark", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMark(@RequestBody Map<String, Object> map) {
        return markService.selectMark(map);
    }

    /**
     * 已点评学生列表查询成绩
     *by 王传营  2020-01-6
     * @param map
     */
    @RequestMapping(value = "/selectMarkGrade", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMarkGrade(@RequestBody Map<String, Object> map) {
        return markService.selectMarkGrade(map);
    }

    /**
     * 评测查询中学生详细信息查询的接口
     *
     * @param map 张鸿铭
     *            2018.12.6 21:20
     */
    @RequestMapping(value = "/selectMarkComment", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMarkComment(@RequestBody Map<String, Object> map) {
        return markService.selectNotMarkComment(map);
    }


    /**
     * 评测查询中教师提交信息的接口
     *
     * @param markEntity 张鸿铭
     *                   2018.12.7 9:30
     */
    @RequestMapping(value = "/markEntity", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity pushMark(@RequestBody MarkEntity markEntity) {
        return markService.pushMark(markEntity);
    }
}

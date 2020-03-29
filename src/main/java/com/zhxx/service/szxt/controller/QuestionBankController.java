package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;
import com.zhxx.service.szxt.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/4.
 */
@Controller
@RequestMapping("/question")

public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 题库查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectQuestionBank", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectQuestionBank(@RequestBody Map<String, String> map) {
        return questionBankService.selectQuestionBank(map);
    }

    /**
     * 题库添加接口
     *
     * @param questionBankEntity
     * @return
     */
    @RequestMapping(value = "/addQuestionBank", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity addQuestionBank(@RequestBody QuestionBankEntity questionBankEntity) {
        return questionBankService.addQuestionBank(questionBankEntity);
    }

    /**
     * 教师预览试卷信息
     *
     * @param questionBankEntity
     * @return
     */
    @RequestMapping(value = "/problemAndSolution", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity problemAndSolution(@RequestBody QuestionBankEntity questionBankEntity) {
        return questionBankService.problemAndSolution(questionBankEntity);
    }


    /**
     * 试卷删除接口
     *
     * @return
     * @author： 张鸿铭
     * @param： questionBankEntity
     * @date:2018/12/6 14:47
     */
    @RequestMapping(value = "/deleteQuestionBank", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity deleteQuestionBank(@RequestBody QuestionBankEntity questionBankEntity) {
        return questionBankService.deleteQuestionBank(questionBankEntity);
    }

}
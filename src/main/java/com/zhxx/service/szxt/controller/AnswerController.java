package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.AnswerEntity;
import com.zhxx.service.szxt.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Created by huasheng on 2018/12/6.
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;


    /**
     * 学生端提交测评题答案
     * 参数List<AnswerEntity>   实体添加了 studentId  学生id   questionId 题库id   字段
     * 韦良伟
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "/answerQuestion", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity answerQuestion(@RequestBody List<AnswerEntity> list) {
        return answerService.answerQuestion(list);
    }
}

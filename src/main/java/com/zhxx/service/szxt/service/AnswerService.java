package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.AnswerEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by huasheng on 2018/12/6.
 */
public interface AnswerService {


    public HttpResponseEntity answerQuestion(List<AnswerEntity> list);

}

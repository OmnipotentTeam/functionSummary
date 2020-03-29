package com.zhxx.service.szxt.service;


import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.QuestionBankEntity;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/4.
 */

public interface QuestionBankService {

    public HttpResponseEntity selectQuestionBank(Map<String, String> map);

    public HttpResponseEntity addQuestionBank(QuestionBankEntity questionBankEntity);

    public HttpResponseEntity problemAndSolution(QuestionBankEntity questionBankEntity);

    public HttpResponseEntity deleteQuestionBank(QuestionBankEntity questionBankEntity);
}




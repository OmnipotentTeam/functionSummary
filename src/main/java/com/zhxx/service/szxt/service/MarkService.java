package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.MarkEntity;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/6.
 */

public interface MarkService {

    public HttpResponseEntity selectMarkComplete(MarkEntity markEntity);

    public HttpResponseEntity selectNotMark(Map<String, Object> map);

    public HttpResponseEntity selectNotMarkComment(Map<String, Object> map);

    public HttpResponseEntity selectMark(Map<String, Object> map);

    public HttpResponseEntity selectMarkGrade(Map<String, Object> map);

    //    public HttpResponseEntity selectMarkComment(Map<String,Object> map);
    public HttpResponseEntity pushMark(MarkEntity markEntity);
}

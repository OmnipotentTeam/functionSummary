package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.MajorEntity;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/10.
 */
public interface MajorService {

    public HttpResponseEntity selectMajorAll(Map<String, String> map);

    public HttpResponseEntity insertMajor(MajorEntity majorEntity);

    public HttpResponseEntity updateMajor(MajorEntity majorEntity);

    public HttpResponseEntity deleteMajor(MajorEntity majorEntity);

    public HttpResponseEntity selectMajor();
}

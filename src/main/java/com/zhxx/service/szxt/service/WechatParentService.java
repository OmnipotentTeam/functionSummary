package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.WechatParentEntity;

import java.util.Map;

public interface WechatParentService {

    Map<String, String> judgebinding(WechatParentEntity wechatParentEntity);

    Map<String, String> binding(WechatParentEntity wechatParentEntity);

    Map<String, String> deletebinding (WechatParentEntity wechatParentEntity);

    HttpResponseEntity selectParent(String id);

    HttpResponseEntity selectStudentId(WechatParentEntity wechatParentEntity);
}

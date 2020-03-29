package com.zhxx.service.szxt.service;

import java.util.Map;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.AccessModelEntity;
import com.zhxx.service.szxt.entity.AccessModelOptionCommonEntity;

public interface AssessFunService {
    //添加模型  
	public HttpResponseEntity addAssessModel(AccessModelOptionCommonEntity accessModelOptionCommonEntity);
	//修改模型
	public HttpResponseEntity updateAssessModel(AccessModelOptionCommonEntity accessModelOptionCommonEntity);
	//删除模型
	public HttpResponseEntity deleteAssessModel(AccessModelEntity accessModelEntity);
	//查询模型
	public HttpResponseEntity queryAssessModel( Map<String, String> map);
	//查询详细信息
	public HttpResponseEntity detailMsgAssessModel(AccessModelEntity accessModelEntity);
	//字典下拉选项框
	public HttpResponseEntity detailmsgselect(String dicttype);
	//查询模型和选项
	public HttpResponseEntity queryModelOptionService( Map<String, String> map);
	
}

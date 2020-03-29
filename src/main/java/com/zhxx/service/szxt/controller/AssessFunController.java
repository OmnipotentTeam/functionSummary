package com.zhxx.service.szxt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.AccessModelEntity;
import com.zhxx.service.szxt.entity.AccessModelOptionCommonEntity;
import com.zhxx.service.szxt.service.AssessFunService;

@RestController
@RequestMapping("/assess")
public class AssessFunController {
	
	@Autowired
	private AssessFunService assessFunService;
	
	//增加模型
	@RequestMapping("/add")
	public HttpResponseEntity addAssessModel(@RequestBody AccessModelOptionCommonEntity accessModelOptionCommonEntity){
		
		return assessFunService.addAssessModel(accessModelOptionCommonEntity);
	}
    //修改模型
	@RequestMapping("/update")
	public HttpResponseEntity updateAssessModel(@RequestBody AccessModelOptionCommonEntity accessModelOptionCommonEntity){
		
		return assessFunService.updateAssessModel(accessModelOptionCommonEntity);
	}
	//删除模型
	@RequestMapping("/delete")
	public HttpResponseEntity deleteAssessModel(@RequestBody AccessModelEntity accessModelEntity){
		
		return assessFunService.deleteAssessModel(accessModelEntity);
	}
	//查询模型列表
	@RequestMapping("/query")
	public HttpResponseEntity queryAssessModel(@RequestBody Map<String, String> map){
		
		
		return assessFunService.queryAssessModel(map);
	}
	//查看模型详细信息
	@RequestMapping("/detailmsg")
	public HttpResponseEntity detailmsgAssessModel(@RequestBody AccessModelEntity accessModelEntity){
		
		return assessFunService.detailMsgAssessModel(accessModelEntity);
	}
	//下拉选项框信息
	@RequestMapping("/detailmsgselect")
	public HttpResponseEntity detailmsgselect(@RequestBody Map<String, String> map){
		
		return assessFunService.detailmsgselect(map.get("dicttype"));
	}
	//查询模型列表带选项
	@RequestMapping("/querymodeloption")
	public HttpResponseEntity queryModelOption(@RequestBody Map<String, String> map){
		
		
		return assessFunService.queryModelOptionService(map);
	}
	
}

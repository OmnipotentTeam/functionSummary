package com.zhxx.service.szxt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.GrowthPlanCommonEntity;

@Service
public interface GrowthPlanService {
    
	public HttpResponseEntity addGrowthPlannSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity queryGrowthPlannSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity finishoutputSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity submitoutputSerivce(List<GrowthPlanCommonEntity> growthPlanCommonEntityList);
	public HttpResponseEntity queryreportSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity queryoutputSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity queryGrowthPlannAppointGradeSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	public HttpResponseEntity addreportcommentSerivce(GrowthPlanCommonEntity growthPlanCommonEntity);
	//判断有无成长规划
	public HttpResponseEntity planningbeService(GrowthPlanCommonEntity growthPlanCommonEntity);
	
	
}

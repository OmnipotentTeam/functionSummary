package com.zhxx.service.szxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.GrowthPlanCommonEntity;
import com.zhxx.service.szxt.entity.GrowthPlanningEntity;
import com.zhxx.service.szxt.service.GrowthPlanService;

@RestController
@RequestMapping("/growth")
public class GrowthPlanController {
     
	@Autowired
	private GrowthPlanService growthPlanService;
	 //添加成长规划
	@RequestMapping("/add")
	public HttpResponseEntity addGrowthPlannController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.addGrowthPlannSerivce(growthPlanCommonEntity);
	}
	 //查询成长规划
	@RequestMapping("/query")
	public HttpResponseEntity queryGrowthPlannController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.queryGrowthPlannSerivce(growthPlanCommonEntity);
	}
	//提交成果物
	@RequestMapping("/finishoutput")
	public HttpResponseEntity finishoutputController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.finishoutputSerivce(growthPlanCommonEntity);
	}
	
	//提交点评
	@RequestMapping("/submitoutput")
	public HttpResponseEntity submitoutputController(@RequestBody List<GrowthPlanCommonEntity> growthPlanCommonEntity){
		
		return growthPlanService.submitoutputSerivce(growthPlanCommonEntity);
	}
	
	//查询成绩单
	@RequestMapping("/queryreport")
	public HttpResponseEntity queryreportController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.queryreportSerivce(growthPlanCommonEntity);
	}
	//查询成果物
		@RequestMapping("/queryoutput")
		public HttpResponseEntity queryoutputController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
			
			return growthPlanService.queryoutputSerivce(growthPlanCommonEntity);
		}
	//按指定年纪查询成长规划
	@RequestMapping("/queryAppointGrade")
	public HttpResponseEntity queryGrowthPlannAppointGradeController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.queryGrowthPlannAppointGradeSerivce(growthPlanCommonEntity);
	}
	//教师评价
	@RequestMapping("/addcomment")
	public HttpResponseEntity addreportcommentController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		
		return growthPlanService.addreportcommentSerivce(growthPlanCommonEntity);
	}
	//判断有无成长规划
	@RequestMapping("/planningbe")
	public HttpResponseEntity planningbeController(@RequestBody GrowthPlanCommonEntity growthPlanCommonEntity){
		return growthPlanService.planningbeService(growthPlanCommonEntity);
	}
	

}

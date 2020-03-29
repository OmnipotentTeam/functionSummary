package com.zhxx.service.szxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxx.service.szxt.entity.GrowthPlanCommonEntity;

@Mapper
public interface GrowthPlannEntityMapper {
     public List<GrowthPlanCommonEntity> querygrowthList(GrowthPlanCommonEntity growthPlanCommonEntity);
     public List<GrowthPlanCommonEntity> querygrowthReportList(GrowthPlanCommonEntity growthPlanCommonEntity);
     public List<GrowthPlanCommonEntity> querygrowthOutputList(GrowthPlanCommonEntity growthPlanCommonEntity);
     
}

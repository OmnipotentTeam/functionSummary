package com.zhxx.service.szxt.service;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.DimensionEntity;

import java.util.Map;

/**
 * Created by dell on 2018/12/11.
 */
public interface DimensionService {
    public HttpResponseEntity selectDimensionService(Map<String, Object> map);

    public HttpResponseEntity updateDimensionService(DimensionEntity dimensionEntity);

    public HttpResponseEntity deleteDimensionService(DimensionEntity dimensionEntity);

    public HttpResponseEntity selectAllDimensionService();

    public HttpResponseEntity insertDimensionService(DimensionEntity dimensionEntity);
}






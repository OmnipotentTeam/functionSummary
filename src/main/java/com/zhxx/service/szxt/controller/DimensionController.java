package com.zhxx.service.szxt.controller;


import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.DimensionEntity;
import com.zhxx.service.szxt.service.DimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 维度的增删改查controller层
 *
 * @author:高明凯 Created by dell on 2018/12/6.
 */
@RestController
@RequestMapping(value = "/system")
public class DimensionController {
    @Autowired
    private DimensionService dimensionService;

    /**
     * 查询所有维度
     * 传入参数：map
     * pageSize(int)
     * pageNum(int)
     *
     * @return
     * @author:高明凯
     * @date:2018/12/6. 15.20
     */
    @PostMapping(value = "/selectDimension", headers = "Accept=application/json")
    public HttpResponseEntity selectCompany(@RequestBody Map<String, Object> map) {
        return dimensionService.selectDimensionService(map);
    }

    /**
     * 添加维度
     * 参数：status(String)
     * dimensionName(String)
     * dimensionId(String)
     *
     * @param
     * @return
     * @author:高明凯
     * @date:2018/12/6. 15.40
     */
    @PostMapping(value = "/insertDimension", headers = "Accept=application/json")
    public HttpResponseEntity insertDimension(@RequestBody DimensionEntity dimensionEntity) {
        return dimensionService.insertDimensionService(dimensionEntity);
    }

    /**
     * 根据id删除维度
     * 传入参数:dimensionId(string)
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6. 15.50
     */
    @PostMapping(value = "/deleteDimension", headers = "Accept=application/json")
    public HttpResponseEntity deleteDimension(@RequestBody DimensionEntity dimensionEntity) {
        return dimensionService.deleteDimensionService(dimensionEntity);
    }

    /**
     * 修改维度
     * 参数：status(String)
     * dimensionName(String)
     * dimensionId(String)
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6. 15.50
     */
    @PostMapping(value = "/updateDimension", headers = "Accept=application/json")
    public HttpResponseEntity selectCompany(@RequestBody DimensionEntity dimensionEntity) {
        return dimensionService.updateDimensionService(dimensionEntity);
    }

    /**
     * 查询所有维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/11. 10.00
     */
    @PostMapping(value = "/SelectAllDimension", headers = "Accept=application/json")
    public HttpResponseEntity selectAllDimension() {
        return dimensionService.selectAllDimensionService();
    }
}

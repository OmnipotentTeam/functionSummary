package com.zhxx.admin.server.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhxx.admin.server.annotation.LogAnnotation;
import com.zhxx.admin.server.dto.BeanField;
import com.zhxx.admin.server.dto.GenerateDetail;
import com.zhxx.admin.server.dto.GenerateInput;
import com.zhxx.admin.server.service.GenerateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码生成接口
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @ApiOperation("根据表名显示表信息")
    @GetMapping(params = {"tableName"})
    @RequiresPermissions("generate:edit")
    public GenerateDetail generateByTableName(String tableName) {
        GenerateDetail detail = new GenerateDetail();
        detail.setBeanName(generateService.upperFirstChar(tableName));
        List<BeanField> fields = generateService.listBeanField(tableName);
        detail.setFields(fields);

        return detail;
    }

    @LogAnnotation
    @ApiOperation("生成代码")
    @PostMapping
    @RequiresPermissions("generate:edit")
    public void save(@RequestBody GenerateInput input) {
        generateService.saveCode(input);
    }

}

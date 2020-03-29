package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.MajorEntity;
import com.zhxx.service.szxt.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by huasheng on 2018/12/10.
 */
@Controller
@RequestMapping("/system")
public class MajorController {

    @Autowired
    private MajorService majorService;

    /**
     * pc端 查看所有专业
     * pageNum 页数 pageSize 条数  majorName 专业名称(为空查询全部)
     * 韦良伟
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectMajorAll", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMajorAll(@RequestBody Map<String, String> map) {
        return majorService.selectMajorAll(map);
    }


    /**
     * 添加专业
     * majorName 专业名称
     * 韦良伟
     *
     * @param majorEntity
     * @return
     */
    @RequestMapping(value = "/insertMajor", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity insertMajor(@RequestBody MajorEntity majorEntity) {
        return majorService.insertMajor(majorEntity);
    }

    /**
     * 修改专业
     * MajorEntity实体对象    majorId （专业的id ）    majorName （专业名称）
     * 韦良伟
     *
     * @param majorEntity
     * @return
     */
    @RequestMapping(value = "/updateMajor", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity updateMajor(@RequestBody MajorEntity majorEntity) {
        return majorService.updateMajor(majorEntity);
    }

    /**
     * 删除专业
     * MajorEntity实体对象    majorId （专业的id ）
     * 韦良伟
     *
     * @param majorEntity
     * @return
     */
    @RequestMapping(value = "/deleteMajor", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity deleteMajor(@RequestBody MajorEntity majorEntity) {
        return majorService.deleteMajor(majorEntity);
    }

    /**
     * 专业字典表
     * pageNum
     * 张鸿铭
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/selectMajor", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectMajor() {
        return majorService.selectMajor();
    }
}

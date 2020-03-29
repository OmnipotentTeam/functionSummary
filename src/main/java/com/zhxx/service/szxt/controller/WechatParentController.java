package com.zhxx.service.szxt.controller;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.WechatParentEntity;
import com.zhxx.service.szxt.service.WechatParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wechatparent")
public class WechatParentController {

    @Autowired
    private WechatParentService wechatParentService;

    /**判断家长绑定*/
    @RequestMapping (value = "/judge/binding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,String> judgebinding (@RequestBody WechatParentEntity wechatParentEntity){
        return wechatParentService.judgebinding(wechatParentEntity);
    }

    /**家长绑定*/
    @RequestMapping (value = "/binding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,String> binding (@RequestBody WechatParentEntity wechatParentEntity){
        return wechatParentService.binding(wechatParentEntity);
    }

    /**删除绑定*/
    @RequestMapping (value = "/deletebinding", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,String> deletebinding (@RequestBody WechatParentEntity wechatParentEntity){
        return wechatParentService.deletebinding(wechatParentEntity);
    }

    /**
     * by 王传营  2020-01-14
     * 根据学生id查询绑定家长信息
     * @param id
     * @return
     */
    @RequestMapping (value = "/selectParent/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectParent (@PathVariable("id") String id){
        return wechatParentService.selectParent(id);
    }

    /**
     * by 王传营  2020-01-15
     * 根据openid查询绑定家长信息
     * @param wechatParentEntity
     * @return
     */
    @RequestMapping (value = "/selectStudentId", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public HttpResponseEntity selectStudentId (@RequestBody WechatParentEntity wechatParentEntity){
        return wechatParentService.selectStudentId(wechatParentEntity);
    }

}

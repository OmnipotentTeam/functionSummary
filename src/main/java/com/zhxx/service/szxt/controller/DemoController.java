package com.zhxx.service.szxt.controller;


import com.zhxx.admin.server.page.table.PageTableRequest;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/sel")
    public HttpResponseEntity demosel() {
        HttpResponseEntity demo = demoService.demoselService();
        return demo;
    }

    @RequestMapping("/sell")
    public HttpResponseEntity listDemo(PageTableRequest request) {
        return demoService.demoselService();
    }
}
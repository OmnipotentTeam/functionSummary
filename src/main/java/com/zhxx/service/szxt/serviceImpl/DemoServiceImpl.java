package com.zhxx.service.szxt.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxx.service.szxt.entity.Demosel;
import com.zhxx.service.szxt.mapper.DemoMapper;
import com.zhxx.service.szxt.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public HttpResponseEntity demoselService() {
        List<Demosel> list = new ArrayList<Demosel>();
        list = demoMapper.queryAll();
        return HttpResponseEntity.seccuss(list);
    }


}

package com.zhxx.service.szxt.config;


import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RemoteProperties remoteProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/imgFiles/**").addResourceLocations("file:/mnt/imgFiles");
        registry.addResourceHandler("/imgFiles/**").addResourceLocations("file:" + Const.ConstInter.PATH_URL);
        super.addResourceHandlers(registry);
    }
}
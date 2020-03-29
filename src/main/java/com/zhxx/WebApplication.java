package com.zhxx;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author wzpstart
 * @date 2019/1/16 - 21:09
 */
public class WebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(ServerApplication.class);
    }
}

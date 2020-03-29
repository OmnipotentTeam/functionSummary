package com.zhxx.service.szxt.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhxx.service.szxt.utils.wx.TokenUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: BaseRequest
 * @Auther: 王新春
 * @Date: 2018/12/10 15:23
 * @Description:
 */

@Component
public class BaseRequest {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * get请求
     *
     * @param url
     * @return
     * @throws ParseException
     * @throws IOException
     * @author 王新春
     */
    public static JSONObject doGet(String url) {
        logger.info("执行get请求开始");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
            logger.info("执行get请求结束");
        } catch (Exception e) {
            logger.error("执行get请求异常");
            e.printStackTrace();
        } finally {
            try {
                //        释放连接
                httpGet.releaseConnection();
                client.close();
            } catch (IOException e) {
                logger.error("执行get请求流关闭异常");
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    /**
     * POST请求
     *
     * @param url
     * @param outStr
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject doPost(String url, String outStr) {
        logger.info("执行post请求开始");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        String result = null;
        try {
            httpost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = client.execute(httpost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("执行post请求结束");
        } catch (IOException e) {
            logger.error("执行post请求异常");
            e.printStackTrace();
        } finally {
            try {
                //释放连接
                httpost.releaseConnection();
                client.close();
            } catch (IOException e) {
                logger.error("执行post请求流关闭异常");
                e.printStackTrace();
            }
        }
        return JSONObject.parseObject(result);
    }
}

package com.zhxx.service.szxt.utils.wx;/**
 * Created by Administrator on 2018/12/14.
 */

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.utils.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: OpenIdUtils
 * @Auther: 王新春
 * @Date: 2018/12/14 20:09
 * @Description:
 */
public class OpenIdUtils {
    private static final Logger logger = LoggerFactory.getLogger(OpenIdUtils.class);


    public static String getOpenId(String code) {
        String url = Const.ConstInter.GET_OPENID.replace("APPID", Const.ConstInter.APP_APPID).replace("SECRET", Const.ConstInter.APPSECRET).replace("JSCODE", code);
        JSONObject jsonObject = BaseRequest.doGet(url);
//        System.out.println(jsonObject.toString());
        String errCode = jsonObject.getString("errcode");


        if (StringUtils.isEmpty(errCode) && !StringUtils.isEmpty(jsonObject.getString("openid").toString())) {
            logger.info("openid获取成功，内容={}", jsonObject.toJSONString());
            String openid = jsonObject.get("openid").toString();
            return openid;
        } else {
            logger.error("openid获取失败，内容={}", jsonObject.toJSONString());
            return null;
        }
    }

}

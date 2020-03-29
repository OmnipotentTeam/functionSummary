package com.zhxx.service.szxt.utils.wx;


import com.alibaba.fastjson.JSONObject;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.entity.vo.AccessToken;
import com.zhxx.service.szxt.utils.BaseRequest;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;


/**
 * @ClassName: TokenUtils
 * @Auther: 王新春
 * @Date: 2018/12/10 15:29
 * @Description:
 */

public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public static AccessToken accessToken = null;

    /**
     * 获取accessToken
     *
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String getAccessToken() {
        if (accessToken == null || accessToken.getCreateTime().getTime() + Long.parseLong(accessToken.getExpiresin()) * 1000 < new Date().getTime()) {
            String url = Const.ConstInter.ACCESS_TOKEN_URL.replace("APPID", Const.ConstInter.APP_APPID).replace("APPSECRET", Const.ConstInter.APPSECRET);
            JSONObject jsonObject = BaseRequest.doGet(url);
            System.out.println(jsonObject.toString());
            if (jsonObject.getString("errorcode") == null) {
                accessToken = new AccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresin(jsonObject.getString("expires_in"));
                accessToken.setCreateTime(new Date());
            }
            logger.info("token获取成功，AccessToken={}", accessToken.toString());
        }
        return accessToken.getAccessToken();
    }
}

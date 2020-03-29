package com.zhxx.service.szxt.utils.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.entity.vo.AppTemplateData;
import com.zhxx.service.szxt.entity.vo.MpTemplateData;
import com.zhxx.service.szxt.entity.vo.WxMssVo;
import com.zhxx.service.szxt.service.AnswerService;
import com.zhxx.service.szxt.utils.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SendMessageUtils
 * @Auther: 王新春
 * @Date: 2018/12/11 19:10
 * @Description:
 */
public class SendMessageUtils {
    private static final Logger logger = LoggerFactory.getLogger(SendMessageUtils.class);

    /**
     * 说明：给指定openId的用户推送消息
     *
     * @param
     * @author： 王新春
     * @return： 1  成功    0：失败
     * @exception：
     * @date： 2018/12/11 19:37
     */
    public static int sendMessage(String openId) {
        MpTemplateData mpTemplateData = new MpTemplateData();//公众号模板

        AppTemplateData appTemplateData = new AppTemplateData();//小程序模板

        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setMp_template_msg(mpTemplateData);
        wxMssVo.setWeapp_template_msg(appTemplateData);
        String token = TokenUtils.getAccessToken();
        wxMssVo.setAccess_token(token);
        wxMssVo.setTouser(openId);
        String url = Const.ConstInter.SEND_MESSAGE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = BaseRequest.doPost(url, JSON.toJSONString(wxMssVo));
        if (Integer.parseInt(jsonObject.get("errcode").toString()) == 0) {
            logger.info("推送消息成功");
            return 1;
        } else {
            logger.error("推送消息失败");
            return 0;
        }
    }
}

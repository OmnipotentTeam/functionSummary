package com.zhxx.service.szxt.entity.vo;

import java.util.Map;

/**
 * @ClassName: WxMssVo
 * @Auther: 王新春
 * @Date: 2018/12/11 15:54
 * @Description: 小程序推送所需数据
 */
public class WxMssVo {
    private String access_token;//access_token
    private String touser;//用户openid
    private AppTemplateData weapp_template_msg;//小程序模板相关信息
    private MpTemplateData mp_template_msg;//公众号模板相关信息

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public AppTemplateData getWeapp_template_msg() {
        return weapp_template_msg;
    }

    public void setWeapp_template_msg(AppTemplateData weapp_template_msg) {
        this.weapp_template_msg = weapp_template_msg;
    }

    public MpTemplateData getMp_template_msg() {
        return mp_template_msg;
    }

    public void setMp_template_msg(MpTemplateData mp_template_msg) {
        this.mp_template_msg = mp_template_msg;
    }

    @Override
    public String toString() {
        return "WxMssVo{" + "access_token='" + access_token + '\'' + ", touser='" + touser + '\'' + ", weapp_template_msg=" + weapp_template_msg + ", mp_template_msg='" + mp_template_msg + '\'' + '}';
    }
}


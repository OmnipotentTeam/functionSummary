package com.zhxx.service.szxt.entity.vo;

/**
 * @ClassName: MpTemplateData
 * @Auther: 王新春
 * @Date: 2018/12/11 16:15
 * @Description: 微信公众号相关数据
 */
public class MpTemplateData {
    private String appid;//公众号appid ， 要求与小程序绑定且同主体
    private String template_id;//公众号模板id
    private String url;//公众号跳转路径
    private String miniprogram;//公众号所能跳转的小程序 ， 必须与公众号有绑定关系
    private String data;//公众号模板数据

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(String miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MpTemplateData{" +
                "appid='" + appid + '\'' +
                ", template_id='" + template_id + '\'' +
                ", url='" + url + '\'' +
                ", miniprogram='" + miniprogram + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

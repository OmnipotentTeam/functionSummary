package com.zhxx.service.szxt.entity.vo;/**
 * Created by Administrator on 2018/12/11.
 */

/**
 * @ClassName: AppTemplateData
 * @Auther: 王新春
 * @Date: 2018/12/11 16:12
 * @Description: 小程序模板信息
 */
public class AppTemplateData {

    private String template_id; //小程序模板id
    private String page; //小程序页面路径
    private String form_id; //小程序模板消息formid
    private String data; //小程序模板数据
    private String emphasis_keyword; //小程序模板放大关键词

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }
}

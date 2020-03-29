package com.zhxx.service.szxt.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by myz on 2017/7/14.
 */

@Configuration
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
@PropertySource(value = {"classpath:config/remote.properties"}, encoding = "utf-8")

@Component
public class RemoteProperties {

    private String uploadFilesUrl;
    private String uploadPicUrl;
    private String httpUrl;
    private String baseUrl;
    private String webUrl;

    public String getUploadFilesUrl() {
        return uploadFilesUrl;
    }

    public void setUploadFilesUrl(String uploadFilesUrl) {
        this.uploadFilesUrl = uploadFilesUrl;
    }

    public String getUploadPicUrl() {
        return uploadPicUrl;
    }

    public void setUploadPicUrl(String uploadPicUrl) {
        this.uploadPicUrl = uploadPicUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getMsgTemplateModuleCodeReg() {
        return msgTemplateModuleCodeReg;
    }

    public void setMsgTemplateModuleCodeReg(String msgTemplateModuleCodeReg) {
        this.msgTemplateModuleCodeReg = msgTemplateModuleCodeReg;
    }

    public String getMsgTemplateModuleCodeFindPsw() {
        return msgTemplateModuleCodeFindPsw;
    }

    public void setMsgTemplateModuleCodeFindPsw(String msgTemplateModuleCodeFindPsw) {
        this.msgTemplateModuleCodeFindPsw = msgTemplateModuleCodeFindPsw;
    }

    public String getMsgSignName() {
        return msgSignName;
    }

    public void setMsgSignName(String msgSignName) {
        this.msgSignName = msgSignName;
    }

    public String getCodeLegalTime() {
        return codeLegalTime;
    }

    public void setCodeLegalTime(String codeLegalTime) {
        this.codeLegalTime = codeLegalTime;
    }

    private String msgTemplateModuleCodeReg;
    private String msgTemplateModuleCodeFindPsw;
    private String msgSignName;

    private String codeLegalTime;
}

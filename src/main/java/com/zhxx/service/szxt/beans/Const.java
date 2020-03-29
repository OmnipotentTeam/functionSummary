package com.zhxx.service.szxt.beans;


/**
 * Created by geely
 */
public interface Const {

    /**
     * 常量定义区
     */
    public interface ConstInter {
        //        public static final String APPSECRET = "76fa05af63a30b583a3cfc5a9560e17f";
//        public static final String APP_APPID = "wx0d91a1af47122812";//小程序appid
//        public static final String APPSECRET = "76fa05af63a30b583a3cfc5a9560e17f";
//        public static final String APP_APPID = "wxbc8ce7fce4c0c0ee";//小程序appid
//        public static final String APPSECRET = "6ac99e713c9f506998940d36d898a80f";    我的小程序
//        public static final String APP_APPID = "wx1d3d700e13bd588a";//小程序appid
        public static final String APPSECRET = "f4e5f07f536ffecf3540c0223308f8bd";   // 姜
        public static final String APP_APPID = "wx41ebc2f35ebd9679";//小程序appid
        public static final String MP_APPID = "";//公众号appid
        public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";//获取accesstoken网址
        public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN";//发送信息网址
        public static final String GET_OPENID = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        public static final String PATH_URL = "D:/X/";

        public static final String APP_TEMPLATE_ID = "";//小程序模板id
        public static final String APP_PAGE = "";//小程序跳转页面路径
        public static final String MP_TEMPLATE_ID = "";//公众号模板id

        public static final String MP_PAGE = "";//公众号跳转页面路径
        public static final String MINIPROGRAM = "";//公众号跳转的小程序

        public  static final String EQUIPMENT_NOSTR = "SZY";

    }

    /**
     * 公共状态码
     */
    public enum PublicCode implements Const {
        SUCCESS(10000, "成功"),
        TASKSEE(12300, "查看任务"),
        ENDTask(66666, "任务已结束"),
        REGISTER(11111, "请注册"),
        REPARENT(80000, "跳转到家长页面"),
        TEACHER(90000, "跳转到教师页面"),
        ADDError(12345, "任务添加成功"),
        WAITTask(12050, "请等待点评"),
        ANSWER(10055, "请答题"),
        WAIT(12000, "等待点评"),
        QueryTask(12005, "请选择任务"),
        NonModifiable(77777, "不可修改的"),
        GRADUATION(23333, "毕业"),
        ERROR(10001, "失败");


        private String msg;
        private int code;

        PublicCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 系统异常状态吗
     */
    public enum SystemCode implements Const {
        UNKNOW_ERROR(10001, "UnknowError", "未知错误"),
        BAD_REQUEST(10001, "BadRequest", "请求有误"),
        FORBIDDEN(10001, "Forbidden", "权限不足"),
        UNAUTHORIZED(10001, "Unauthorized", "没有权限"),
        NOT_FOUND(10001, "NotFound", "您所访问的资源不存在"),
        SERVICE_EXCEPTION(10001, "Custom exception", "自定义异常"),
        METHOD_NOT_ALLOWED(10001, "Method Not Allowed", "请求方式不正确"),
        SERVER_EPT(10001, "ServerEpt", "操作异常，请稍后再试");

        private int code;
        private String msgEn;
        private String msgCn;

        SystemCode(int code, String msgCn, String msgEn) {
            this.code = code;
            this.msgCn = msgCn;
            this.msgEn = msgEn;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsgEn() {
            return msgEn;
        }

        public void setMsgEn(String msgEn) {
            this.msgEn = msgEn;
        }

        public String getMsgCn() {
            return msgCn;
        }

        public void setMsgCn(String msgCn) {
            this.msgCn = msgCn;
        }
    }

}

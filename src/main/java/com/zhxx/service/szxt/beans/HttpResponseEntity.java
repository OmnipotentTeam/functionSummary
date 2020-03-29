package com.zhxx.service.szxt.beans;


import java.io.Serializable;

/**
 * Created by myz on 2017/7/8.
 */

public class HttpResponseEntity implements Serializable {

    private int code; //状态码
    private Object data; //内容
    private String message; //状态消息

    public HttpResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpResponseEntity(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public HttpResponseEntity(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //成功方法
    public static HttpResponseEntity seccuss(String msg) {
        return new HttpResponseEntity(Const.PublicCode.SUCCESS.getCode(), msg);
    }

    public static HttpResponseEntity seccuss(Object msg) {
        return new HttpResponseEntity(Const.PublicCode.SUCCESS.getCode(), msg);
    }
    public static HttpResponseEntity seccuss(Object msg,String str) {
        return new HttpResponseEntity(Const.PublicCode.SUCCESS.getCode(), msg,str);
    }
    //失败方法
    public static HttpResponseEntity error(String msg) {
        return new HttpResponseEntity(Const.PublicCode.ERROR.getCode(), msg);
    }

//    //成功方法
//    public static  HttpResponseEntity RE(int code){
//        return new HttpResponseEntity(code,null);
//    }
}

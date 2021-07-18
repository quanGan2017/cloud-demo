package com.example.cloudcomm.base;

public class AppResult {

    // 响应业务状态
    private String code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public AppResult() {
    }
    public AppResult(Object data) {
        this.code = "200";
        this.message = "OK";
        this.data = data;
    }
    public AppResult(String message, Object data) {
        this.code = "200";
        this.message = message;
        this.data = data;
    }

    public AppResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static AppResult success() {
        return new AppResult("200","成功",null);
    }

    public static AppResult success(Object data) {
        return new AppResult("200","成功",data);
    }
    public static AppResult success(String message, Object data) {
        return new AppResult("200",message, data);
    }
    public static AppResult fail(String code,String message, Object data) {
        return new AppResult(code,message, data);
    }

}

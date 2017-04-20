package com.yin.trip.admin.entity;

/**
 * Created by yinfeng on 2017/4/18 0018.
 *  返回结果实体类
 */
public class Result {
    private boolean result;             //结果，成功为true,失败为false

    private String message;             //结果说明

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

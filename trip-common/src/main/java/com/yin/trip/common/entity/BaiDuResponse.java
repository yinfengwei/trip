package com.yin.trip.common.entity;

/**
 * Created by yinfeng on 2017/4/8 0008.
 *
 *  百度返回格式
 */
public class BaiDuResponse {
    private int status;     //数据返回结果
    private BaiDuResult result;     //返回数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaiDuResult getResult() {
        return result;
    }

    public void setResult(BaiDuResult result) {
        this.result = result;
    }
}

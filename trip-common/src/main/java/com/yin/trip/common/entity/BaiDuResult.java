package com.yin.trip.common.entity;

/**
 * Created by yinfeng on 2017/4/8 0008.
 *  百度API result对象
 */
public class BaiDuResult {
    private int precise;            //位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
    private int confidence;         //可信度，描述打点准确度
    private String level;       //地址类型
    private BaiDuLocation location;     //经纬度

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BaiDuLocation getLocation() {
        return location;
    }

    public void setLocation(BaiDuLocation location) {
        this.location = location;
    }
}

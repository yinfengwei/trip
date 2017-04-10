package com.yin.trip.common.util;

import com.alibaba.fastjson.JSON;
import com.yin.trip.common.entity.BaiDuLocation;
import com.yin.trip.common.entity.BaiDuResponse;
import org.slf4j.LoggerFactory;

/**
 * Created by yinfeng on 2017/3/27 0027.
 * 百度API接口
 */
public class BaiDuApi {

    private static String transUrl = "http://api.map.baidu.com/geocoder/v2/";

    private static String timeUrl = "http://api.map.baidu.com/direction/v1";

    /**
     *  坐标转化，将地址转化为百度地图坐标
     * @param address
     * @return
     */
    public static BaiDuLocation translate(String address){

        String param = "output=json&pois=1&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
                   + "&mcode=49:A8:2E:E4:F5:39:65:2D:6D:A4:00:EC:8F:01:35:83:E8:26:F1:01;com.yin.trip";



        String result = HttpUtil.sendGet(transUrl, param + "&address=" + address);

        System.out.print(result);

        BaiDuResponse response = JSON.parseObject(result, BaiDuResponse.class);


        if(response.getStatus() == 0) {
            return response.getResult().getLocation();
        } else {
            return null;
        }

    }

    /**
     *  求百度地图坐标之间的距离
     * @param location
     * @param destination
     * @return
     */
    public static double distance(BaiDuLocation location, BaiDuLocation destination){

        //纬度
        double lat1 = (Math.PI / 180) * location.getLat();
        double lat2 = (Math.PI / 180) * destination.getLat();

        //经度
        double lon1 = (Math.PI / 180) * location.getLng();
        double lon2 = (Math.PI / 180) * destination.getLng();

        //地球半径
        double R = 6370996.81;

        //计算两点之间的距离
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d;
    }

    /**
     *  求百度地图坐标之间不同交通方式的最短时间
     * @param location
     * @param destination
     * @return
     */
    public static String getTime(BaiDuLocation location,BaiDuLocation destination,String type){


        String request = StringUtil.buildWithParam(timeUrl ,
                "mode", type, "origin" , String.format("%.6f",location.getLat()) + "," + String.format("%.6f",location.getLng()),
                "destination" ,String.format("%.6f", destination.getLat()) + "," + String.format("%.6f",destination.getLng()),
                "region", "深圳", "origin_region", "深圳", "destination_region", "深圳" ,"output" ,"json",
                "ak", "caR16D6gFwi9voPC3H32RlXrNShv4Fvn",
                "mcode", "49:A8:2E:E4:F5:39:65:2D:6D:A4:00:EC:8F:01:35:83:E8:26:F1:01;com.yin.trip");

        String result = HttpUtil.sendGet(request,null);

        System.out.print(result);

        return result;

    }



}

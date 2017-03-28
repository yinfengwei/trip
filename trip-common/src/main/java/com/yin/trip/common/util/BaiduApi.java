package com.yin.trip.common.util;

/**
 * Created by yinfeng on 2017/3/27 0027.
 * 百度API接口
 */
public class BaiduApi {

    private static String transUrl = "http://api.map.baidu.com/geocoder/v2/";
    /**
     *  坐标转化，将地址转化为百度地图坐标
     * @param address
     * @return
     */
    public static String translate(String address){

     String param = "callback=renderReverse&output=json&pois=1&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
               + "&mcode=49:A8:2E:E4:F5:39:65:2D:6D:A4:00:EC:8F:01:35:83:E8:26:F1:01;com.yin.trip";

     String result = HttpUtil.sendGet(transUrl, param + "&address=" + address);

     System.out.print(result);

     return result;


    }
}

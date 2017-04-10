package com.yin.trip.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/17 0017.
 * Http 请求类
 */
public class HttpUtil {
    public static String sendGet(String url, String param) {

        String result = "";
        BufferedReader in = null;

        try {
            String sendUrl;
            if (param != null) {
                sendUrl = url + "?" + param;
            }else {
                sendUrl = url;
            }


            System.out.println(sendUrl);

            URL realUrl = new URL(sendUrl);



            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(3000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "text/javascript;charset=utf-8");
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                   System.out.println(key + "--->" + map.get(key));
//            }


            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));

            String line;

            while ((line = in.readLine()) != null) {
                result += line;
            }


        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}

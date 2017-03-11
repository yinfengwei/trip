package com.yin.trip.common.util;

/**
 * Created by yinfeng on 2017/3/11 0011.
 * 字符串操作
 */
public class StringUtil {

    /**
     *  拼接字符串
     *  @param args 参数为key-value 格式
     *  @param str 拼接的参数
     */
    public static String buildWithParam(String str, String ...args){

        StringBuffer ptr = new StringBuffer(str);

        if (args.length % 2 ==0) {
            ptr.append("?");
            for(int i = 0; i < args.length - 2;){
                ptr.append(args[i]);
                ptr.append("=");
                ptr.append(args[i+1]);
                ptr.append("&");
                i += 2;
            }

            ptr.append(args[args.length - 2]);
            ptr.append("=");
            ptr.append(args[args.length - 1]);


        }

        return ptr.toString();
    }
}

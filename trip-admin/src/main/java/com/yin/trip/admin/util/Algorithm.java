package com.yin.trip.admin.util;


import org.apache.log4j.spi.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yinfeng on 2017/3/29 0029.
 *   算法实现
 */
public class Algorithm {

    private static double[][] user;         //用户评分表
    private static int[] itemSum;           //用户表每一行总数
    private static Map<String,Integer> similarUser;     //用户相似度最后返回结果
    private static int userSum;                         //用户总数

    /**
     *  获取用户相似度
     *   userName 已经包含该用户
     * @param userNames
     * @return
     */
    public static Map<String, Object> getSimilar(String userName,
                                                 Map<String,Integer> userNames,
                                                 Map<String, List<String>> scoreChart,
                                                 Map<String, List<String>> clickChart,
                                                 Map<String, List<String>> ageChart,
                                                 Map<String, List<String>> typeChart,
                                                 Map<String, List<String>> sexChart){

        //1.1. 获得与用户相关的用户(类型、位置、评分、点击) 一一对应
        similarUser = userNames;

        displaySimilar();

        //建立表，数量必须大于1
        userSum = similarUser.size();
        System.out.println("获取与用户一起评分过得用户总数" + userSum);
        user = new double[userSum][userSum];
        itemSum = new int[userSum];
        int i = 0 ,j = 0;


        //初始化
        for(i = 0 ; i < userSum; i++) {
            for (j = 0; j < userSum; j++) {
                user[i][j] = 0;
            }
            itemSum[i] = 0;
        }
//        display();
        //通过倒排表计算得分,评分项比重为2 ，点击项比重为1
        System.out.println("添加评分倒排表");
        addScore(scoreChart, 5);
        System.out.println("添加点击倒排表");
        addScore(clickChart, 3);
        System.out.println("添加年龄辅助用户表");
        addScoreByParam(ageChart, 1);
        System.out.println("添加类型辅助用户表");
        addScoreByParam(typeChart, 1);
        System.out.println("添加性别辅助用户表");
        addScoreByParam(sexChart, 1);



//        //从评分倒排表填充数据
//        Set<Map.Entry<String, List<String>>> entries = chart.entrySet();
//
//        for (Map.Entry<String, List<String>> entry : entries) {
//
//            List<String> tempList = entry.getValue();
//
//            //两个用户都有则进行填充
//            for (i = 0; i < tempList.size() - 1; i++) {
//                for (j = 1; j < tempList.size(); j++) {
//                    int a = similarUser.get(tempList.get(i));
//                    System.out.println(tempList.get(j));
//                    int b = similarUser.get(tempList.get(j));
//
//
//                    System.out.println("增加的坐标为:" + a + "," + b);
//                    //获取用户名，然后map对应坐标
//                    user[a][b] += 1;
//                    user[b][a] += 1;
//                    itemSum[a] += 1;
//                    itemSum[b] += 1;
//                }
//            }
//        }

        //计算余弦得分
        calculate();

        //返回结果
        Map<String, Object> similar = new HashMap<String, Object>();

        for (String name : userNames.keySet()) {
            //相似用户不包括该用户本身
            if(!name.equals(userName)) {
                System.out.println(name + "得分" + user[0][similarUser.get(name)] );
                similar.put(name, user[0][similarUser.get(name)]);
            }
        }


        return similar;
    }

    /**
     *  根据倒排表进行计算
     * @param chart
     */
    private static void addScore(Map<String, List<String>> chart, int score){

        //非空
        if (chart != null) {
            //从评分倒排表填充数据
            Set<Map.Entry<String, List<String>>> entries = chart.entrySet();

            for (Map.Entry<String, List<String>> entry : entries) {

                List<String> tempList = entry.getValue();

                //两个用户都有则进行填充
                for (int i = 0; i < tempList.size() - 1; i++) {
                    for (int j = 1; j < tempList.size(); j++) {
                        int a = similarUser.get(tempList.get(i));
                        System.out.println(tempList.get(j));
                        int b = similarUser.get(tempList.get(j));


                        System.out.println("增加的坐标为:" + a + "," + b);
                        //获取用户名，然后map对应坐标
                        user[a][b] += score;
                        user[b][a] += score;
                        itemSum[a] += score;
                        itemSum[b] += score;
                    }
                }
            }
//            display();
        }
    }

    /**
     *  根据用户列表进行计算
     * @param chart
     */
    private static void addScoreByParam(Map<String, List<String>> chart, int score){

        //非空
        if (chart != null) {
            //从用户列表填充数据
            for(String key : chart.keySet()) {
                List<String> tempList = chart.get(key);

                //用户列表进行辅助添加
                for(String tempUser : tempList) {
                    int a = similarUser.get(key);
                    System.out.println("tempUser" + tempUser);
                    int b = similarUser.get(tempUser);


                    System.out.println("增加的坐标为:" + a + "," + b);
                    //获取用户名，然后map对应坐标
                    user[a][b] += score;
                    user[b][a] += score;
                    itemSum[a] += score;
                    itemSum[b] += score;
                }
            }

//            display();
        }
    }

    /**
     *  余弦计算相似度
     *
     */
    private static void calculate(){
        //采用余弦计算
        //初始化
//        for(i = 0 ; i < userSum; i++) {

        for (int j = 0; j < userSum; j++) {
            user[0][j] = user[0][j]/Math.sqrt(itemSum[0] * itemSum[j]);
            System.out.print(user[0][j] + " ");
        }
        System.out.println( "  =" + itemSum[0]);
//        }
    }

    /**
     *  测试用显示user 表
     *
     */
    private static void display(){

        for(int i = 0 ; i < userSum; i++) {

            for (int j = 0; j < userSum; j++) {
//                user[0][j] = user[0][j]/Math.sqrt(itemSum[0] * itemSum[j]);
                System.out.print(user[i][j] + " ");
            }
            System.out.println( "  =" + itemSum[0]);
       }
    }

    /**
     *  测试用显示similar 表
     *
     */
    private static void displaySimilar(){
        System.out.println( "displaySimilar" );
        for(String key : similarUser.keySet()) {

            System.out.println( "key:" + key);
        }
    }
}

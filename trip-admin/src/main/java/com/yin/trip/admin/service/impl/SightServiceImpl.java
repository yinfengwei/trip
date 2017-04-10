package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.SightDao;
import com.yin.trip.admin.entity.Distance;
import com.yin.trip.admin.entity.Score;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.entity.User;
import com.yin.trip.admin.service.ClickService;
import com.yin.trip.admin.service.ScoreService;
import com.yin.trip.admin.service.SightService;
import com.yin.trip.admin.service.UserService;
import com.yin.trip.admin.util.Algorithm;
import com.yin.trip.common.entity.BaiDuLocation;
import com.yin.trip.common.util.BaiDuApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by yinfeng on 2017/3/18 0018.
 */
@Service
public class SightServiceImpl implements SightService{

    @Autowired
    private SightDao sightDao;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClickService clickService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 插入景点信息
     *
     * @param sight
     */
    @Override
    public void insertData(Sight sight) {
        sightDao.InsertData(sight);

    }

    /**
     * 获取景点列表
     *
     * @param start
     * @param end
     */
    @Override
    public List<Sight> getSights(int start, int end) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("start", start);
        map.put("end", end);

        return sightDao.getSights(map);
    }

//    /**
//     * 获取景点列表
//     *
//     * @param id
//     */
//    @Override
//    public List<Sight> getSightsById(int id) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("id", id);
//
//        return sightDao.getSights(map);
//    }

    /**
     * 获取景点列表
     */
    @Override
    public List<Sight> getSights() {
        return sightDao.getSights(null);
    }

    /**
     * 根据景点名获取景点信息
     *
     * @param name
     * @return
     */
    @Override
    public Sight getSightByName(String name) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", name);


        return sightDao.getSightByParam(map);
    }

    /**
     * 更新景点数据
     *
     * @param rank
     */
    @Override
    public void update(String name, int rank) {

        Sight sight = getSightByName(name);

        if (sight != null) {

            sight.setRank(rank);

            sightDao.update(sight);
            logger.info("更新景点排名成功");
        }

    }

    /**
     * 更新景点数据
     *
     * @param sight
     */
    @Override
    public void updateAll(Sight sight) {
        sightDao.update(sight);
    }

    /**
     * 更新景点评分与评分人数
     *
     * @param name
     * @param
     */
    @Override
    public void updateScore(String name, float userScore ,long userSum) {
        Sight sight = getSightByName(name);

        if (sight != null) {

            sight.setUserScore(userScore);
            sight.setUserSum(userSum);

            sightDao.update(sight);
            logger.info("更新景点得分数据成功");
        }
    }

    /**
     * 获取景点总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return sightDao.count();
    }

    /**
     * 推荐景点
     * //获取每个景点的距离比例，距离得分为2.5分比例
     */
    @Override
    public List<Entry<Sight, Double>> getRecommend(String userName, Map<String, Double> locationScore) {

        //1、获得用户相似度
        Map<String,Object> similar = getSimilar(userName);

        //2、对所有景点进行分析获得推荐
        //获取所有景点
        List<Sight> sights = getSights();


        //获取每个景点的得分
        Map<Sight,Double> recommend = new HashMap<Sight, Double>();


        //用户曾经评论的景点赋值为0
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userName", userName);

        List<Score> scores = scoreService.getScoreList(map);
        List<String> sightName = new ArrayList<String>();

        //已经去过的景点
        for(Score score : scores) {
            recommend.put(getSightByName(score.getSightName()), locationScore.get(score.getSightName()) * 2);
            sightName.add(score.getSightName());

//            sights.remove(getSightByName(score.getSightName()));
            logger.info("景点：" + getSightByName(score.getSightName()).getName() + "只有位置得分为 : " + locationScore.get(score.getSightName()) * 2);
        }

//        logger.info("景点后总数：" + recommend.size() + recommend.containsKey(getSightByName("世界之窗")));
//        Sight tempsight = getSightByName("世界之窗");
//        logger.info("是否包含世界之窗" + recommend.containsKey(tempsight));



        for(Sight sight : sights) {

            if (sightName.contains(sight.getName())) {
                logger.info("跳过景点：" + sight.getName());
                continue;
            }

            double recommendScore = 0;
            //如果尚未有用户进行评分则考虑冷启动使用携程评分占一定比例
            //携程得分80%，用户平均评分 40%，如果有用户评分则占1
            if (sight.getUserScore() == 0) {

                double weight = 0;

                //携程评价总数最多为15615,以10为底，按照评价人数进行得分的筛选比例,权重0.2 - 1
                if (sight.getSum() < 10) {
                    weight = 0.2;
                } else {

                    weight = 0.2 * (Math.log(sight.getSum()) / Math.log(10) + 1);

                }
                //添加冷启动占分比例
                recommendScore += (weight * sight.getScore())/5 * 2;

                //添加位置得分
                recommendScore += locationScore.get(sight.getName()) * 2;

            } else {
                //被评分
                Map<String, Object> sightMap = new HashMap<String, Object>();

                sightMap.put("sightName", sight.getName());

                List<Score> sightScore = scoreService.getScoreList(sightMap);


                for (Score score : sightScore) {

                    Double tScore = (Double) similar.get(score.getUserName());

                    //如果在用户相似度中则进行计算
                    if (tScore != null) {
                        recommendScore += tScore * score.getScore();
                    }
                }

                //如果评分为0 ，则以用户平均得分为准比例为2.5
                if (recommendScore == 0) {

                    recommendScore += sight.getUserScore()/5 * 2;
                } else {
                    //计算相似度得分
                    recommendScore = recommendScore/5 * 3;


                }

                recommendScore += locationScore.get(sight.getName()) * 2;
            }

            recommend.put(sight, recommendScore);

        }

        //排序
        List<Entry<Sight, Double>> enList = new ArrayList<Entry<Sight, Double>>(recommend.entrySet());
        Collections.sort(enList, new Comparator<Map.Entry<Sight, Double>>() {
            public int compare(Map.Entry<Sight, Double> o1, Map.Entry<Sight, Double> o2) {
                Double a = o1.getValue() - o2.getValue();
                if (a == 0) {
                    return 0;
                } else if (a > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });


//        for (int i = 0; i < enList.size(); i++) {
//
//           Entry<Sight, Double> entry  = enList.get(i);
//
//
//           System.out.println(entry.getKey().getName() + entry.getValue());
//        }


//        return enList.get(enList.size() - 1).getKey();

        return enList;
    }

    /**
     *  计算用户相似度
     */
    public Map<String, Object> getSimilar(String userName) {

        //获取评分[1-2] 以及 [3-5]分别的用户集以及倒排表
//        List<String> userNames1 = scoreService.getSimUserByName(userName,1,2);
//        Map<String, List<String>> chart1 = scoreService.getScoreChart(userName, 1, 2);
        //获取点击用户集
        List<String> clickUserNames = clickService.getSimUserByName(userName);
        Map<String, Object> result ;

        logger.info(clickUserNames.size() + "");

        //若点击用户集为空则使用用户注册信息中按照年龄、类型、性别进行分析
        if (clickUserNames.size() == 0) {
//           logger.info("进入冷启动");
            User user = userService.getUserByUseName(userName);
            result = new HashMap<String, Object>();

            //获取所有相关用户，包括年龄、类型、性别相同的相关用户
            List<String> userNames = userService.getCorrelationUser(userName);

            //添加该用户至最后一位
            userNames.add(user.getUserName());

            //加上本用户
            int num = userNames.size();

            int[][] userList = new int[num][3];
            int i = 0,j = 0;

            for (i = 0; i < num; i++) {
                for (j = 0; j < 3; j++) {
                    userList[i][j] = 0;
                }
            }

            //循环
            for (i = 0; i < num; i++ ){
                //填入信息
                //类别
                User tempUser = userService.getUserByUseName(userNames.get(i));
                if(tempUser.getType().equals(user.getType())) {
                    userList[i][0] = 1;
                }
                if(tempUser.getAge() == user.getAge()) {
                    userList[i][1] = 1;
                }
                if(tempUser.getSex() == user.getSex()) {
                    userList[i][2] = 1;
                }
            }

//            System.out.println("获得矩阵为");
//            for (i = 0; i < num; i++) {
//                for (j = 0; j < 3; j++) {
//                    System.out.print(userList[i][j] + " ");
//                }
//                System.out.println();
//            }

            //计算相似度,最后一个是原来的用户不需要计算
            for (i = 0; i < num - 1 ; i++) {
                result.put(userNames.get(i), getUserSimilar(userList[i],userList[num - 1]));
//                System.out.println(userNames.get(i) + "相似度为" + result.get(userNames.get(i)));
            }



        } else {
            //若点击用户集不为空则用户注册信息中按照年龄、类型、性别可作为辅助分析，点击数据以及评分3分以上为主要分析
            //获取评分表
            List<String> scoreUserNames = scoreService.getSimUserByName(userName,3,5);

            //1.1. 获得与用户相关的用户(类型、位置、评分、点击) 一一对应
            Map<String, Integer> similarUser = new HashMap<String, Integer>();

            //先添加该用户
            similarUser.put(userName, 0);

            int count = 1;
            //添加与该用户评分过同个项目3分以上的用户
            for (String user : scoreUserNames) {
                similarUser.put(user,count);
                count++;
            }
            //添加与该用户点击过同个项目的用户，去重
            for (String user : clickUserNames) {
                if (!similarUser.containsKey(user)) {
                    similarUser.put(user,count);
                    count++;
                }
            }

            logger.info("相似用户数目（包括该用户）:" + similarUser.size());

            //获取相关用户中年龄、类型、性别与该用户相同的数据
            Map<String, List<String>> ageChart = userService.getUserListByAge(similarUser);

            Map<String, List<String>> typeChart = userService.getUserListByType(similarUser);

            Map<String, List<String>> sexChart = userService.getUserListBySex(similarUser);

            Map<String, List<String>> scoreChart = scoreService.getScoreChart(userName, 3, 5);

            Map<String, List<String>> clickChart = clickService.getClickChart(userName);

            //综合两个相似度
            Map<String, Object> similarByScore = Algorithm.getSimilar(userName,similarUser,scoreChart,clickChart, ageChart, typeChart ,sexChart);

//            Map<String, Object> similarByClick = Algorithm.getSimilar(userName,clickUserNames,clickChart);

            //如果评分相关用户为0 则使用点击相似度
            result = similarByScore;


        }

//
//



//        //1.1. 获得与用户相关的用户(类型、位置、评分、点击) 一一对应
//        Map<String,Integer> similarUser = new HashMap<String, Integer>();
//
//        //先添加该用户
//        similarUser.put(userName, 0);
//
//        int count = 1;
//
//        //将评分分为两部分[1-2],[3-5]两种
//        List<String> userNames1 = scoreService.getSimUserByName(userName,1,2);
//        List<String> userNames2 = scoreService.getSimUserByName(userName,3,5);
//
//
//        //添加与该用户评分过同个项目的用户
//        for (String user : userNames1) {
//            similarUser.put(user,count);
//            count++;
//        }
//
//        for (String user : userNames2) {
//            similarUser.put(user,count);
//            count++;
//        }
//
//        //景点 - 用户代排表 评分为 [1,2] 与 [3, 5]
//        Map<String, List<String>> chart1 = scoreService.getScoreChart(userName, 1, 2);
//        Map<String, List<String>> chart2 = scoreService.getScoreChart(userName, 3, 5);
//
//        //建立表，数量必须大于1
//        int userSum = count;
//
//        logger.info("获取与用户一起评分过得用户总数" + userSum);
//        double[][] user = new double[userSum][userSum];
//        int itemSum[] = new int[userSum];
//        int i = 0 ,j = 0;
//
//
//        //初始化
//        for(i = 0 ; i < userSum; i++) {
//            for (j = 0; j < userSum; j++) {
//                user[i][j] = 0;
//            }
//            itemSum[i] = 0;
//        }
//
//        //从评分[3, 5] 的评分倒排表填充数据
//        Set<Map.Entry<String, List<String>>> entries1 = chart1.entrySet();
//
//        Set<Map.Entry<String, List<String>>> entries2 = chart2.entrySet();
//
//        for (Map.Entry<String, List<String>> entry : entries1) {
//
//            List<String> tempList = entry.getValue();
//
//            //两个用户都有则进行填充
//            for (i = 0; i < tempList.size() - 1; i++) {
//                for (j = 1; j < tempList.size(); j++) {
//                    int a = similarUser.get(tempList.get(i));
//                    int b = similarUser.get(tempList.get(j));
//
//                    logger.info("增加的坐标为:" + a + "," + b);
//                    //获取用户名，然后map对应坐标
//                    user[a][b] += 1;
//                    user[b][a] += 1;
//                    itemSum[a] += 1;
//                    itemSum[b] += 1;
//                }
//            }
//        }
//
//        for (Map.Entry<String, List<String>> entry : entries2) {
//
//            List<String> tempList = entry.getValue();
//
//            //两个用户都有则进行填充
//            for (i = 0; i < tempList.size() - 1; i++) {
//                for (j = 1; j < tempList.size(); j++) {
//                    int a = similarUser.get(tempList.get(i));
//                    int b = similarUser.get(tempList.get(j));
//
//                    logger.info("增加的坐标为:" + a + "," + b);
//                    //获取用户名，然后map对应坐标
//                    user[a][b] += 1;
//                    user[b][a] += 1;
//                    itemSum[a] += 1;
//                    itemSum[b] += 1;
//                }
//            }
//        }
//
//        //采用余弦计算
//        //初始化
////        for(i = 0 ; i < userSum; i++) {
//
//            for (j = 0; j <userSum; j++) {
//                user[0][j] = user[0][j]/Math.sqrt(itemSum[0] * itemSum[j]);
//                System.out.print(user[0][j] + " ");
//            }
//            System.out.println( "  =" + itemSum[0]);
////        }
//
//        Map<String, Object> similar = new HashMap<String, Object>();
//
//        for (String name : userNames1) {
//            similar.put(name, user[0][similarUser.get(name)]);
//        }
//        for (String name : userNames2) {
//            similar.put(name, user[0][similarUser.get(name)]);
//        }

        return result;
    }

    //整数计算皮卡尔德算法
    private double getUserSimilar(int[] user1, int[] user2) {
        int n = 0;// 数量n

        int size = user1.length;

        for (int i = 0; i < size; i++) {

            n += Math.pow((user1[i] - user2[i]), 2) ;

        }

        double sim = 1.0 / (1 + Math.sqrt(n));





        // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
//        double sd = sxy - sx * sy / n;
//        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
//
//        logger.info("n = " + n + ",sxy=" + sxy + " ,sx=" + sx + ",sy=" + sy);
//        logger.info("n = " + n + ",sx2=" + sx2 + " ,sy2=" + sy2);

        logger.info("sim = " + sim );
        return sim;
    }

    /**
     * 获取景点与当前位置的距离并排序
     *
     * @param location
     * @return
     */
    @Override
    public List<Distance> getDistance(BaiDuLocation location) {

        List<Distance> result = new ArrayList<Distance>();

        //获取所有景点数据
        for(Sight sight : getSights()) {

            BaiDuLocation destination = new BaiDuLocation();

            destination.setLng(Double.parseDouble(sight.getLongitude()));
            destination.setLat(Double.parseDouble(sight.getLatitude()));

            //计算距离
            Distance distance = new Distance();

            distance.setSight(sight);
            distance.setDistance(BaiDuApi.distance(location, destination));


            result.add(distance);


        }

        //排序
        Collections.sort(result, new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                //按照景点的距离进行升序排列
                if(o1.getDistance() > o2.getDistance()){
                    return 1;
                }
                if(o1.getDistance() == o2.getDistance()){
                    return 0;
                }
                return -1;
            }
        });
        return result;
    }


    /**
     * 根据距离信息对景点进行距离得分比例计算
     *
     * @param distances
     * @return
     */
    @Override
    public Map<String, Double> getDistanceScore(List<Distance> distances) {

        //获取最大值与最小值
        double maxDistance = distances.get(distances.size() - 1).getDistance();
        double minDistance = distances.get(0).getDistance();

//        logger.info("max :" + maxDistance);
//        logger.info("min : " + minDistance);
        Map<String, Double> result = new HashMap<String, Double>();

        for (Distance distance : distances) {
            double qa = 1 - (distance.getDistance() - minDistance)/(maxDistance - minDistance);
//            logger.info(distance.getSight().getName() + ":" + qa);
            result.put(distance.getSight().getName(), qa);
        }


        return result;
    }
}

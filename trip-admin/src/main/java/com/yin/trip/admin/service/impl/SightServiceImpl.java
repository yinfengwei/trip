package com.yin.trip.admin.service.impl;

import com.yin.trip.admin.dao.SightDao;
import com.yin.trip.admin.entity.Sight;
import com.yin.trip.admin.service.SightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinfeng on 2017/3/18 0018.
 */
@Service
public class SightServiceImpl implements SightService{

    @Autowired
    private SightDao sightDao;

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
     * 更新景点评分与评分人数
     *
     * @param name
     * @param score
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
}

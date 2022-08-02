package com.xiangge.edusta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.commonutils.R;
import com.xiangge.edusta.client.UCenterClient2;
import com.xiangge.edusta.entity.StatisticsDaily;
import com.xiangge.edusta.mapper.StatisticsDailyMapper;
import com.xiangge.edusta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>     aZb1wiWVfy,R
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-31
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UCenterClient2 client;

    @Override
    public Integer registerCount(String day) {
        R r = client.countRegister(day);
        Map<String, Object> data = r.getData();
        Integer countRegister = (Integer) data.get("countRegister");
        StatisticsDaily daily = new StatisticsDaily();
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        StatisticsDaily one = baseMapper.selectOne(wrapper);
        if (one == null) {
            daily.setDateCalculated(day);//统计日期
            daily.setCourseNum(100);
            daily.setLoginNum(200);
            daily.setVideoViewNum(300);
            daily.setRegisterNum(countRegister);
            baseMapper.insert(daily);
        } else {
            one.setRegisterNum(countRegister);
            baseMapper.updateById(one);
        }

        return countRegister;
    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> list = baseMapper.selectList(wrapper);
        //前端JSON--对应---->后端list
        List<String> dateList = new ArrayList<>();//日期
        List<Integer> numList = new ArrayList<>();//数量

        for (int i = 0; i < list.size(); i++) {
            StatisticsDaily daily = list.get(i);
            dateList.add(daily.getDateCalculated());//封装日期
            switch (type) {
                case "login_num":
                    numList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("data_calculatedList", dateList);
        map.put("numDataList", numList);
        return map;
    }
}

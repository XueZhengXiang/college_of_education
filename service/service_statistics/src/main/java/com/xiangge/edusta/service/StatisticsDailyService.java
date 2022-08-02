package com.xiangge.edusta.service;

import com.xiangge.edusta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-31
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    Integer registerCount(String day);

    Map<String,Object> showData(String type, String begin, String end);
}

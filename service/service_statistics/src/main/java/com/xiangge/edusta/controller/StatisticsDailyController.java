package com.xiangge.edusta.controller;


import com.xiangge.commonutils.R;
import com.xiangge.edusta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/edusta/stadaily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    @PostMapping("/registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        Integer count = dailyService.registerCount(day);
        return R.ok();
    }

    //图表显示
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = dailyService.showData(type, begin, end);
        return R.ok().data(map);

    }
}


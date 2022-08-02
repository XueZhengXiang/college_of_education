package com.xiangge.edusta.schedule;

import com.xiangge.edusta.service.StatisticsDailyService;
import com.xiangge.edusta.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService dailyService;

    //    @Scheduled(cron = "0/3 * * * * ?")
//        public void t() {
//        System.out.println("每隔3秒钟执行一次");
//    }
    //凌晨1点统计前一天的数据
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleTask() {
        dailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}

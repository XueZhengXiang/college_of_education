package com.xiangge.edusta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 祥哥
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.xiangge")
@MapperScan("com.xiangge.edusta.mapper")
@EnableFeignClients
@EnableScheduling//开启定时任务
public class EduStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduStatisticsApplication.class, args);
    }
}

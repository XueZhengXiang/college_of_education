package com.xiangge.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 祥哥
 * @version 1.0
 */
@ComponentScan(basePackages = "com.xiangge")
@MapperScan("com.xiangge.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients//调用UCenter和course模块
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);

    }
}
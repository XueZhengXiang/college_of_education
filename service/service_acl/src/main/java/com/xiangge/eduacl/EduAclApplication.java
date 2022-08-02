package com.xiangge.eduacl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 祥哥
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.xiangge.eduacl.mapper")
@ComponentScan("com.xiangge")
@EnableDiscoveryClient
public class EduAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduAclApplication.class, args);
    }
}

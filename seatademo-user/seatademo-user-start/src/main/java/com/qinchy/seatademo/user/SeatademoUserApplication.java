package com.qinchy.seatademo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 用户服务启动类
 *
 * @author qinchy
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class SeatademoUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatademoUserApplication.class, args);
    }

}

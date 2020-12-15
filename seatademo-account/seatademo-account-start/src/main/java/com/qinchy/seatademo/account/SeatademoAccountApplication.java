package com.qinchy.seatademo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账户服务启动类
 *
 * @author  qinchy
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients
public class SeatademoAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatademoAccountApplication.class, args);
    }

}

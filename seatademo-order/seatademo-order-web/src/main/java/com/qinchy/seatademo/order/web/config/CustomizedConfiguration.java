package com.qinchy.seatademo.order.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: qinchy
 * @Date: 2020/8/8 22:17
 * @Description: ${description}
 */
@Configuration
public class CustomizedConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

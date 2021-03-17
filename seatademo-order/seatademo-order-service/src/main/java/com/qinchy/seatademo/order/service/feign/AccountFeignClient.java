package com.qinchy.seatademo.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "seatademo-account", url = "http://192.168.36.1:18085")
public interface AccountFeignClient {

    @GetMapping("/account/reduce")
    Boolean reduce(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money);
}

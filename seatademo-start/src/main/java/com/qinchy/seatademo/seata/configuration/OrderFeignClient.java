package com.qinchy.seatademo.seata.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用订单服务
 *
 * @author Administrator
 * @date 2020/12/15 11:41
 */
@FeignClient(value = "seatademo-order", url = "http://127.0.0.1:18083")
public interface OrderFeignClient {
    @PostMapping(path = "/order/create")
    Boolean create(@RequestParam("userId") String userId,
                   @RequestParam("commodityCode") String commodityCode,
                   @RequestParam("orderCount") int orderCount);
}

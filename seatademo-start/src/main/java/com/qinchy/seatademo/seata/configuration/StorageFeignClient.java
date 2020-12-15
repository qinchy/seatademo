package com.qinchy.seatademo.seata.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用库存服务
 *
 * @author Administrator
 * @date 2020/12/15 11:40
 */
@FeignClient(value = "seatademo-storage", url = "http://127.0.0.1:18082")
public interface StorageFeignClient {

    @RequestMapping(path = "/storage/deduct/{commodityCode}/{count}")
    Boolean deduct(@RequestParam("commodityCode") String commodityCode,
                   @RequestParam("count") int count);
}

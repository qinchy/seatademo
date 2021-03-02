package com.qinchy.seatademo.seata.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用库存服务
 *
 * @author qinchy
 * @date 2020/12/15 11:40
 */
//@FeignClient(value = "seatademo-storage", url = "http://127.0.0.1:18082")
@FeignClient(value = "seatademo-storage")
public interface StorageFeignClient {

    /**
     * 通过请求参数的方式请求减库存接口
     * 这里会构造一个http://seatademo-storage/storage/deduct/{commodityCode}/{count}的请求
     *
     * @param commodityCode 商品代码
     * @param count 商品数量
     * @return {@link Boolean}
     **/
    @RequestMapping(path = "/storage/deduct/{commodityCode}/{count}")
    Boolean deductByPathVariable(@PathVariable("commodityCode") String commodityCode,
                   @PathVariable("count") int count);

    /**
     * 通过请求参数的方式请求减库存接口
     * 这里会构造一个http://seatademo-storage/storage/deduct?commodityCode=xxx&count=xxx的请求
     *
     * @param commodityCode 商品代码
     * @param count 商品数量
     * @return {@link Boolean}
     **/
    @RequestMapping(path = "/storage/deduct")
    Boolean deductByRequestParam(@RequestParam("commodityCode") String commodityCode,
                   @RequestParam("count") int count);
}

/*
 * Copyright (C) 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qinchy.seatademo.order.web.controller;

import com.qinchy.seatademo.order.api.OrderService;
import com.qinchy.seatademo.order.api.model.OrderModel;
import com.qinchy.seatademo.order.service.feign.AccountFeignClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * 订单controller
 *
 * @author qinchy
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final String USER_ID = "U100001";

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountFeignClient accountFeignClient;

    /**
     * 创建订单
     *
     * @param userId        账户编号
     * @param commodityCode 商品代码
     * @param orderCount    商品数量
     * @return {@link String}
     **/
    @PostMapping(value = "/create")
    @GlobalTransactional
    public Boolean createByRequestParam(@RequestParam("userId") String userId,
                                        @RequestParam("commodityCode") String commodityCode,
                                        @RequestParam("orderCount") Integer orderCount) {
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setCommodityCode(commodityCode);
        orderModel.setCount(orderCount);

        return createByJson(orderModel);
    }

    /**
     * 创建订单
     *
     * @param userId        账户编号
     * @param commodityCode 商品代码
     * @param orderCount    商品数量
     * @return {@link String}
     **/
    @PostMapping(value = "/create/{userId}/{commodityCode}/{orderCount}")
    @GlobalTransactional
    public Boolean createByPathVariable(@PathVariable("userId") String userId,
                                        @PathVariable("commodityCode") String commodityCode,
                                        @PathVariable("orderCount") Integer orderCount) {
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setCommodityCode(commodityCode);
        orderModel.setCount(orderCount);

        return createByJson(orderModel);
    }

    /**
     * 创建订单
     *
     * @param orderModel 订单实体
     * @return {@link Boolean}
     **/
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @GlobalTransactional
    public Boolean createByJson(@RequestBody OrderModel orderModel) {
        LOGGER.info("Order Service Begin ... xid: " + RootContext.getXID());

        BigDecimal orderMoney = calculate(orderModel.getCommodityCode(), orderModel.getCount());
        reduceAccount(orderMoney, "feign");

        orderModel.setMoney(orderMoney);
        OrderModel order2 = orderService.createOrder(orderModel);

        LOGGER.info("Order Service End ... Created " + order2);

        if (null != order2.getId()) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }


    /**
     * 计算订单金额
     *
     * @param commodityId 商品代码
     * @param orderCount  商品数量
     * @return {@link BigDecimal}
     **/
    private BigDecimal calculate(String commodityId, int orderCount) {
        return new BigDecimal(2 * orderCount);
    }

    /**
     * HTTP方式调用账户服务
     *
     * @param orderMoney 订单金额
     * @return {@link Boolean}
     **/
    private Boolean reduceAccount(BigDecimal orderMoney) {
        String url = "http://127.0.0.1:18084/account/reduce";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("userId", USER_ID);
        map.add("money", orderMoney.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request,
                String.class);

        String body = response.getBody();

        if (StringUtils.equalsIgnoreCase(body, Boolean.TRUE.toString())) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * HTTP方式调用账户服务
     *
     * @param orderMoney 订单金额
     * @return {@link Boolean}
     **/
    private Boolean reduceAccount(BigDecimal orderMoney,String type) {
        if (!StringUtils.endsWithIgnoreCase(type,"feign")){
            return reduceAccount(orderMoney);
        }

        return accountFeignClient.reduce(USER_ID, orderMoney);
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return {@link Boolean}
     */
    @RequestMapping("/create/commit")
    public Boolean create1() {
        // product-1 扣库存时模拟了一个正常业务,
        orderService.createOrder("1", "product-1", 1);
        return true;
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return
     */
    @RequestMapping("/create/rollback")
    public Boolean create2() {
        // product-2 扣库存时模拟了一个业务异常,
        orderService.createOrder("1", "product-2", 1);
        return true;
    }

}

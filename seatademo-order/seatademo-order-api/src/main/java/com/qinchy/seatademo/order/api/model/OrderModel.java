package com.qinchy.seatademo.order.api.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class OrderModel {

    /**
     * 订单编号
     **/
    private Integer id;

    /**
     * 账户编号
     **/
    private String userId;

    /**
     * 商品代码
     **/
    private String commodityCode;

    /**
     * 商品库存
     **/
    private Integer count;

    /**
     * 订单金额
     **/
    private BigDecimal money;
}
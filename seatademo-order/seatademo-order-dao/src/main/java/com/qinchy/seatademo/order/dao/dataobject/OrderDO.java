package com.qinchy.seatademo.order.dao.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class OrderDO {

    /**
     * 订单编号
     **/
    private Long id;

    /**
     * 账户编号
     **/
    private String userId;

    /**
     * 商品编号
     **/
    private String commodityCode;

    /**
     * 账户数量
     **/
    private Integer count;

    /**
     * 订单金额
     **/
    private BigDecimal money;
}

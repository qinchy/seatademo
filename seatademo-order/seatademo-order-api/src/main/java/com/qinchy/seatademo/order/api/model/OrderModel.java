package com.qinchy.seatademo.order.api.model;

import lombok.Data;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class OrderModel {
    private Long id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;
}
package com.qinchy.seatademo.order.dao.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Data
public class OrderDO {
    private Long id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private BigDecimal money;
}

package com.qinchy.seatademo.account.api.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:20
 * @Description: ${description}
 */
@Data
public class AccountModel {

    /**
     * 数据库自增id
     **/
    private Integer id;

    /**
     * 账户编号
     **/
    private String userId;

    /**
     * 账户金额
     **/
    private BigDecimal money;
}

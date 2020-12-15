package com.qinchy.seatademo.account.dao.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:25
 * @Description: ${description}
 */
@Data
public class AccountDO {

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

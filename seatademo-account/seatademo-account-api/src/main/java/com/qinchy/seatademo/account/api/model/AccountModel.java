package com.qinchy.seatademo.account.api.model;

import lombok.Data;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:20
 * @Description: ${description}
 */
@Data
public class AccountModel {
    private Long id;

    private String userId;

    private Integer money;
}

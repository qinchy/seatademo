package com.qinchy.seatademo.account.dao.dataobject;

import lombok.Data;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:25
 * @Description: ${description}
 */
@Data
public class AccountDO {
    private Long id;

    private String userId;

    private Integer money;
}

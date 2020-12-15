package com.qinchy.seatademo.account.api;

import com.qinchy.seatademo.account.api.model.AccountModel;

import java.math.BigDecimal;

/**
 * 账户服务接口
 *
 * @Author: qinchy
 * @Date: 2020/8/7 17:23
 */
public interface AccountService {

    /**
     * 返回账户余额
     *
     * @param userId 用户编号
     * @return {@link Integer}
     **/
    BigDecimal getMoney(String userId);

    /**
     * 添加账户
     *
     * @param account 账户信息
     * @return {@link AccountModel}
     **/
    AccountModel addAccount(AccountModel account);

    /**
     * 扣减账户
     *
     * @param userId TODO
     * @param money TODO
     * @return {@link int}
     **/
    int reduce(String userId, BigDecimal money);
}

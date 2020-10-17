package com.qinchy.seatademo.account.api;

import com.qinchy.seatademo.account.api.model.AccountModel;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:23
 * @Description: ${description}
 */
public interface AccountService {
    Integer getMoney(String userId);

    AccountModel addAccount(AccountModel account);
}

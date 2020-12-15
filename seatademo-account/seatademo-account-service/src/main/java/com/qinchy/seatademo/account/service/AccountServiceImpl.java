package com.qinchy.seatademo.account.service;

import com.qinchy.seatademo.account.api.AccountService;
import com.qinchy.seatademo.account.api.model.AccountModel;
import com.qinchy.seatademo.account.dao.dataobject.AccountDO;
import com.qinchy.seatademo.account.dao.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 账户服务
 *
 * @author qinchy
 */
@Component
public class AccountServiceImpl implements AccountService {

    private static final BeanCopier copier = BeanCopier.create(AccountModel.class, AccountDO.class, false);

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BigDecimal getMoney(String userId) {
        AccountDO accountDO = accountMapper.getAccountByUserId(userId);
        return accountDO != null ? accountDO.getMoney() : null;
    }

    @Override
    public AccountModel addAccount(AccountModel user) {
        AccountDO accountDO = new AccountDO();
        copier.copy(user, accountDO, null);

        Integer id = accountMapper.createAccount(accountDO);
        user.setId(id);
        return user;
    }

    @Override
    public int reduce(String userId, BigDecimal money) {
        AccountDO accountDO = new AccountDO();
        accountDO.setUserId(userId);
        accountDO.setMoney(money);
        return accountMapper.reduce(accountDO);
    }
}

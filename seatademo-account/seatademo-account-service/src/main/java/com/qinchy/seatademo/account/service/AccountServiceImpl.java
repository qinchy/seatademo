package com.qinchy.seatademo.account.service;

import com.qinchy.seatademo.account.api.AccountService;
import com.qinchy.seatademo.account.api.model.AccountModel;
import com.qinchy.seatademo.account.dao.dataobject.AccountDO;
import com.qinchy.seatademo.account.dao.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    private static final BeanCopier copier = BeanCopier.create(AccountModel.class, AccountDO.class, false);

    @Override
    public Integer getMoney(String userId) {
        AccountDO accountDO = accountMapper.getByUserId(userId);
        return accountDO != null ? accountDO.getMoney(): null;
    }

    @Override
    public AccountModel addAccount(AccountModel user) {
        AccountDO accountDO = new AccountDO();
        copier.copy(user, accountDO, null);

        Long id = accountMapper.insert(accountDO);
        user.setId(id);
        return user;
    }
}

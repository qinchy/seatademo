package com.qinchy.seatademo.account.dao.mapper;

import com.qinchy.seatademo.account.dao.dataobject.AccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: qinchy
 * @Date: 2020/8/7 17:26
 * @Description: ${description}
 */
@Mapper
public interface AccountMapper {

    /**
     * 通过账户编号获取账户信息
     *
     * @param userId 用户编号
     * @return {@link AccountDO}
     **/
    AccountDO getAccountByUserId(String userId);

    /**
     * 通过唯一编号获取账户信息
     *
     * @param id 数据库唯一id
     * @return {@link AccountDO}
     **/
    AccountDO getAccountById(Long id);

    /**
     * 新增账户信息
     *
     * @param account 账户信息
     * @return {@link Long}
     **/
    Integer createAccount(AccountDO account);

    /**
     * 扣减账户
     *
     * @param accountDO 账户信息
     * @return {@link Long}
     **/
    int reduce(AccountDO accountDO);
}

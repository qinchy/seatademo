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

    AccountDO getByUserId(String userId);

    AccountDO getById(Long id);

    Long insert(AccountDO account);
}

package com.qinchy.seatademo.user.dao.mapper;

import com.qinchy.seatademo.user.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @author qinchy
 */
@Mapper
public interface UserMapper {

    /**
     * 通过用户名字获取用户信息
     *
     * @param name 用户名字
     * @return {@link UserDO}
     **/
    UserDO getUserByName(String name);

    /**
     * 通过用户编号获取用户信息
     *
     * @param id 用户名字
     * @return {@link UserDO}
     **/
    UserDO getUserById(Long id);

    /**
     * 添加用户信息
     *
     * @param userDO 用户实体
     * @return {@link Long}
     **/
    Long addUser(UserDO userDO);

    /**
     * 添加用户年龄
     *
     * @param id 用户编号
     * @return {@link int}
     **/
    int addAge(Long id);
}

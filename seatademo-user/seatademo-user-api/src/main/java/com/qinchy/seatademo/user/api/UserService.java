package com.qinchy.seatademo.user.api;


import com.qinchy.seatademo.user.api.model.UserModel;

/**
 * 用户服务接口
 *
 * @author qinchy
 */
public interface UserService {

    /**
     * 通过用户名字获取用户信息
     *
     * @param name 用户名字
     * @return {@link UserModel}
     **/
    UserModel getUserByName(String name);

    /**
     * 通过用户编号获取用户信息
     *
     * @param id 用户名字
     * @return {@link UserModel}
     **/
    UserModel getUserById(Long id);

    /**
     * 添加用户信息
     *
     * @param userModel 用户实体
     * @return {@link UserModel}
     **/
    UserModel addUser(UserModel userModel);

    /**
     * 添加年龄
     *
     * @param id 用户编号
     * @return {@link Boolean}
     **/
    Boolean addAge(Long id);
}

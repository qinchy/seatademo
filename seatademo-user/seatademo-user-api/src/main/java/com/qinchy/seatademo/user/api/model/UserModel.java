package com.qinchy.seatademo.user.api.model;

import lombok.Data;

/**
 * 用户服务
 *
 * @author qinchy
 */
@Data
public class UserModel {

    /**
     * 用户编号
     **/
    private Long id;

    /**
     * 用户名字
     **/
    private String name;

    /**
     * 用户年龄
     **/
    private Integer age;

}
package com.qinchy.seatademo.user.dao.dataobject;

import lombok.Data;

/**
 * 用户数据库对象
 * @author qinchy
 */
@Data
public class UserDO {

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

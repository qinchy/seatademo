package com.qinchy.seatademo.user.dao.mapper;

import com.qinchy.seatademo.user.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Mapper
public interface UserMapper {

    UserDO getUserByName(String name);

    UserDO getUserById(Long id);

    Long addUser(UserDO userDO);
}

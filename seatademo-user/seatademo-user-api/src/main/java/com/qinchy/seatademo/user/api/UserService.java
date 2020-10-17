package com.qinchy.seatademo.user.api;


import com.qinchy.seatademo.user.api.model.UserModel;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface UserService {

    UserModel getUserByName(String name);

    UserModel getUserById(Long id);

    UserModel addUser(UserModel user);
}

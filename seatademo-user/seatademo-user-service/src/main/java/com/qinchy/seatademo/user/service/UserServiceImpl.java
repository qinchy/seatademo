package com.qinchy.seatademo.user.service;

import com.qinchy.seatademo.user.api.UserService;
import com.qinchy.seatademo.user.api.model.UserModel;
import com.qinchy.seatademo.user.dao.dataobject.UserDO;
import com.qinchy.seatademo.user.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Service
public class UserServiceImpl implements UserService {

    private static final BeanCopier COPIER1 = BeanCopier.create(UserModel.class, UserDO.class, false);

    private static final BeanCopier COPIER2 = BeanCopier.create(UserDO.class, UserModel.class, false);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel getUserByName(String name) {
        UserDO userDO = userMapper.getUserByName(name);

        UserModel userModel = new UserModel();
        COPIER2.copy(userDO, userModel, null);

        return userModel;
    }

    @Override
    public UserModel getUserById(Long id) {
        UserDO userDO = userMapper.getUserById(id);

        UserModel userModel = new UserModel();
        COPIER2.copy(userDO, userModel, null);

        return userModel;
    }

    @Override
    public UserModel addUser(UserModel user) {
        UserDO userDO = new UserDO();
        COPIER1.copy(user, userDO, null);

        Long id = userMapper.addUser(userDO);
        user.setId(id);
        return user;
    }

    @Override
    public Boolean addAge(Long id) {
        int count = userMapper.addAge(id);
        if (count == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

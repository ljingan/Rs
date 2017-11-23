package com.module.user.impl;

import com.module.user.UserDao;
import com.module.user.UserEntity;
import com.module.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void saveUser() {
        UserEntity user = new UserEntity();
        user.setName("dddd");
        user.setPermission(1);
        user.setPassword("11111");
        userDao.save(user);
    }

    public UserEntity getUser(String username) {
        return null;
    }
}

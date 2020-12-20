package com.sebc.api.service.impl;

import com.sebc.api.dao.UserDao;
import com.sebc.api.entity.User;
import com.sebc.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByUsername(name);
    }
}

package com.sebc.api.service;

import com.sebc.api.entity.User;

public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @param name
     * @return
     */
    public User findUserByName(String name);
}

package com.sebc.api.service;

import com.sebc.api.entity.User;

public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @param name
     * @return
     */
    User findUserByName(String name);


    /**

     *
     * @param user
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * 根据Id删除用户
     *
     * @param id
     */
    void deleteUserById(String id);
}

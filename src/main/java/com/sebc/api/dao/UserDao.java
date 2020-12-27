package com.sebc.api.dao;

import com.sebc.api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lijie
 * @Date 2020/12/20
 * @Desc 用户表数据访问层
 */

public interface UserDao {

    /**
     * @param userName 用户名称
     * @return 用户信息
     */
    public User findUserByUsername(String userName);

    /**
     * 插入用户
     *
     * @param user
     */
    public User insertUser(User user);


    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    long deleteUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    long updateUser(User user);
}

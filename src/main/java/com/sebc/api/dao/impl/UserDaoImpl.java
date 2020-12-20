package com.sebc.api.dao.impl;

import com.sebc.api.dao.UserDao;
import com.sebc.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findUserByUsername(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        List<User> userList = mongoTemplate.find(query, User.class);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }
}

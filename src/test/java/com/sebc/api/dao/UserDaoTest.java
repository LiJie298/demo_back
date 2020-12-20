package com.sebc.api.dao;

import com.sebc.api.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void findUserByName() {
        User user = userDao.findUserByUsername("2134");
    }
}
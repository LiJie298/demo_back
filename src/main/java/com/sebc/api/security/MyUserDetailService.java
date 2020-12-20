package com.sebc.api.security;

import com.sebc.api.entity.User;
import com.sebc.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author lijie
 * @Date 2020/12/20
 * @Desc
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(s);
        if (user != null) {
            JwtUser jwtUser = new JwtUser(user.getUsername(), user.getPassword());
            return jwtUser;
        }
        throw new UsernameNotFoundException("用户名未找到");

    }
}

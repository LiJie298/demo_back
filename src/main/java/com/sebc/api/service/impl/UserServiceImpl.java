package com.sebc.api.service.impl;

import com.sebc.api.dao.UserDao;
import com.sebc.api.entity.JwtUser;
import com.sebc.api.entity.User;
import com.sebc.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    // 密码加密器
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByUsername(name);
    }

    @Override
    public User createUser(User user) {
        if (StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getUsername())) {
            return null;
        } else {
            if (findUserByName(user.getUsername()) != null) {
                return null;
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDao.insertUser(user);
        }
    }

    @Override
    public void deleteUserById(String id) {
        userDao.deleteUser(new User(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + 0));
        //todo 用户权限，后续如果有可以添加进去
//        for (BeanDefinitionDsl.Role role : userMapper.findRoleByUsername(s)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        }
        return new JwtUser(user.getUsername(), user.getPassword(), authorities);
    }
}

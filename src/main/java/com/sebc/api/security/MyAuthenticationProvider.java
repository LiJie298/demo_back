package com.sebc.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author lijie
 * @Date 2020/12/20
 * @Desc 用户验证登录
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final MyUserDetailService myUserDetailService;

    public MyAuthenticationProvider(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        JwtUser jwtUser = (JwtUser) myUserDetailService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        return new UsernamePasswordAuthenticationToken(jwtUser, jwtUser.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

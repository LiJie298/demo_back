package com.sebc.api.util;

import com.sebc.api.entity.CurrentUser;
import com.sebc.api.entity.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {


    /**
     * 获取当前用户信息
     *
     * @return 信息
     *
     *
     */
    public static CurrentUser getCurrentUser() {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser.getCurrentUser();
    }
}

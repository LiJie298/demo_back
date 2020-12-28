package com.sebc.api.util;

import com.sebc.api.VO.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {


    /**
     * 获取当前用户信息
     *
     * @return 信息
     */
    public static CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}

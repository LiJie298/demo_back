package com.sebc.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author lijie
 * @Date 2020/12/20
 * @Desc 返回code说明
 */
@AllArgsConstructor
@Getter
public enum ResultCode {
    // 成功
    SUCCESS(200, "成功"),
    // 参数验证问题
    PARA_ERROR(301, "参数验证有问题"),
    // 用户验证失败,
    AUTHOR_ERROR(401, "您还没有登录"),
    // 服务器内部异常
    SERVER_ERROR(500, "内部异常，请稍后在试试");


    //值
    private int value;
    //说明
    private String desc;

}

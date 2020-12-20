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
    PARA_ERROR(301, "参数验证有问题"),
    // 用户验证失败
    AUTHOR_ERROR(401, "成功");


    //值
    private int value;
    //说明
    private String desc;

}

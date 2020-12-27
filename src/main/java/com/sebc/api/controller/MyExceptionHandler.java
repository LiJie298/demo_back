package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.util.LogUtil;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理器
 */
@ControllerAdvice(basePackages = "com.sebc.api.controller")
public class MyExceptionHandler {


    @ResponseBody
    @ExceptionHandler({RuntimeException.class, Exception.class, IndexOutOfBoundsException.class})
    public ResultVO handleRuntimeException(Exception e) {
        LogUtil.error("", e);
        return ResultVO.serverErrorResult();
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        LogUtil.error("", e);
        return ResultVO.paramErrorResult();
    }
}

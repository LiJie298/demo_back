package com.sebc.api.VO;

import com.sebc.api.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author lijie
 * @Date 2020/12/20
 * @Desc api统一返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    /**
     * 获取成功的结果
     *
     * @param data 返回数据
     * @return 结果
     */
    public static <T> ResultVO<T> successResult(T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(ResultCode.SUCCESS.getValue());
        resultVO.setMsg(ResultCode.SUCCESS.getDesc());
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 获取成功的结果
     *
     * @return 结果
     */
    public static <T> ResultVO<T> paramErrorResult() {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(ResultCode.PARA_ERROR.getValue());
        resultVO.setMsg(ResultCode.PARA_ERROR.getDesc());
        return resultVO;
    }


}

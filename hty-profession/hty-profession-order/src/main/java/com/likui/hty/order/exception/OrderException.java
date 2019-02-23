package com.likui.hty.order.exception;


import com.likui.hty.order.enums.ResultEnum;

/**
 * Created by likui
 * 2018-10-9 19:49
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}

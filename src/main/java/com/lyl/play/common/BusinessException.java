package com.lyl.play.common;

import com.lyl.play.utils.ErrorCode;

import lombok.Data;

/**
 * 运行时异常(程序内部错误) 如： 1、错误的类型转换 2、数组访问越界 3、访问空指针
 *
 * @author LoongChow
 * @date 2018/11/01
 */
@Data
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String msg;

    public BusinessException(String msg) {
        this(msg, null);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}

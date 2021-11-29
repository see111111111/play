package com.lyl.play.utils;

public enum ErrorCode {
    DEFAULT_SUCCESS_CODE(0, null),
    DEFAULT_ERROR_CODE(1001, "系统错误"),
    PARAM_NOT_EXIST(1003, "参数缺失"),
    NOT_LOGIN(1002, "登陆已失效，请重新登陆"),
    EMAIL_NOT_VARIFY(1011, "邮箱未校验，无法登陆"),
	BIND_ERROR(2001, "绑定钱包异常");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

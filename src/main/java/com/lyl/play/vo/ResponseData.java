package com.lyl.play.vo;

import com.lyl.play.utils.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回消息体")
public class ResponseData<T> {

    @ApiModelProperty("返回消息编码,0代表成功，其它的代表异常")
    private int code;
    @ApiModelProperty("返回的信息，一般在code不为0的时候返回才有意义")
    private String message;
    @ApiModelProperty("返回的具体消息内容")
    private T data;
    @ApiModelProperty("特殊字段，一般情况下无用")
    private Object extra;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public static ResponseData success() {
        return build(ErrorCode.DEFAULT_SUCCESS_CODE.getCode(), ErrorCode.DEFAULT_SUCCESS_CODE.getMessage(), null, null);
    }

    public static <T> ResponseData<T> success(T object) {
        return build(ErrorCode.DEFAULT_SUCCESS_CODE.getCode(), ErrorCode.DEFAULT_SUCCESS_CODE.getMessage(), object,
                null);
    }

    public static ResponseData error(String message) {
        return build(ErrorCode.DEFAULT_ERROR_CODE.getCode(), message, null, null);
    }

    public static ResponseData error(ErrorCode errorCode) {
        return build(errorCode.getCode(), errorCode.getMessage(), null, null);
    }

    public static ResponseData error(ErrorCode errorCode, Object extra) {
        return build(errorCode.getCode(), errorCode.getMessage(), null, extra);
    }

    public static <T> ResponseData<T> error(Integer code, String message, T object) {
        return build(code, message, object, null);
    }

    private static <T> ResponseData<T> build(Integer code, String message, T content, Object extra) {
        ResponseData<T> ret = new ResponseData<>();
        if (code != null) {
            ret.setCode(code);
        }
        if (message != null) {
            ret.setMessage(message);
        }
        if (content != null) {
            ret.setData(content);
        }
        if (extra != null) {
            ret.setExtra(extra);
        }

        return ret;
    }
}

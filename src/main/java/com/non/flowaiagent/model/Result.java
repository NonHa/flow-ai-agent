package com.non.flowaiagent.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.non.flowaiagent.enums.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) // 支持链式调用
@JsonInclude(JsonInclude.Include.NON_NULL) // 为null的字段不序列化到JSON中
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    // 私有构造方法，强制使用静态方法创建
    private Result() {}

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回（无数据）
     */
    public static <T> Result<T> success() {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回（带数据）
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回（自定义消息和数据）
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回（使用枚举）
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回（自定义状态码和消息）
     */
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<T>(code, message, null);
    }

    /**
     * 失败返回（自定义状态码、消息和数据，可用于返回详细错误信息）
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }
}

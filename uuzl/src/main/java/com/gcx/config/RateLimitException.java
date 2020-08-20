package com.gcx.config;

//自定义限流异常

public class RateLimitException extends RuntimeException {

    private Integer code;

    public RateLimitException(int code, String message) {
        super(message);
        this.code = code;
    }
}

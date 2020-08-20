package com.gcx.config;

import jdk.nashorn.internal.objects.annotations.Getter;


public enum ExceptionEnum {
    RATERLIMIT_NO(1, "暂时没有获取到令牌，请稍后再试"),;

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

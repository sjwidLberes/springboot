package com.example.demo.enums;

/**
 * Created by admin on 2017/7/23.
 */
public enum ExceptionEnum {

    SUCCESS(            200,    "成功"),
    PARAM_ERROR(        4000,   "参数错误"),
    SERVER_ERROR(       5000,   "服务器错误"),

    //--参数类错误40**
    USER_NAME_EMPTY(    4001,   "用户名不能为空"),

    //--服务端错误50**
    DB_ERROR(           5001,   "数据库操作错误");

    private final Integer code;

    private final String message;

    ExceptionEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

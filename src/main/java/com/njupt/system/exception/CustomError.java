package com.njupt.system.exception;

import java.util.HashSet;
import java.util.Set;

public enum CustomError {
    INTERNAL_ERROR(5001,"内部错误"),

    ACCOUNT_NULL(5002, "登陆账号为空"),

    PASSWORD_NULL(5003, "输入密码为空"),

    PASSWORD_ERROR(5004, "密码错误！"),

    USER_NOT_EXIT(5005, "用户不存在"),

    NOT_PERMISSION(5006, "权限不足!"),

    DUPLICATE_INSERT(5007, "重复添加！"),

    TOKEN_EXPIRED(300, "Token过期"),

    TOKEN_PARSE_FAIL(301, "Token解析失败"),

    TOKEN_KEY_NOT_MATCH(302, "密钥不匹配"),

    CONTENT_NULL(5008, "账号或密码不能为空！"),

    SIGN_IN_REQUIRED(5009, "尚未登陆！"),

    DELETE_SELF(5010, "不能删除自己哦！"),

    INFORMATION_NOT_EXIT(5011,"该信息不存在"),

    USER_EXIT(5012, "该手机号已被注册"),

    STUDENT_EXIT(5013, "该学生已被注册");

    private int code;

    private String errMsg;

    CustomError(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    //检查枚举的Error号有没有重复的,有异常则抛出（1，内部错误）
    public static void checkDuplicate() {
        Set<Integer> codes = new HashSet<>();
        for (CustomError customError : values()) {
            if (codes.contains(customError.code)) {
                throw new LocalRuntimeException(CustomError.INTERNAL_ERROR, "Duplicate error code " + customError.code);
            } else {
                codes.add(customError.code);
            }
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}

package com.njupt.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:27 AM
 * @signature Do it while you can!
 */
@ToString
@AllArgsConstructor
public enum Permission {

    ROOT(0, "系统管理员"),

    DEPARTMENT(1, "部门管理员"),

    COMMON(2, "普通管理员"),

    STUDENT(3, "学生"),

    PARENT(4, "家长")

    ;

    @Getter
    private Integer code;
    @Getter
    private String msg;

}

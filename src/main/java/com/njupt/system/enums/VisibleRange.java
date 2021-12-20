package com.njupt.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:47 PM
 * @signature Do it while you can!
 */
@ToString
@AllArgsConstructor
public enum VisibleRange {

    PUBLIC(0, "全局可见"),

    SPECIFIED_CLASS(1, "指定班级"),

    SPECIFIED_PERSON(2, "指定学生/家长")

    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;
}

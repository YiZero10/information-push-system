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
public enum Status {

    PASS_AUDIT(0, "审核通过"),

    WAIT_AUDIT(1, "等待审核"),

    FAIL_AUDIT(2, "审核未通过"),

    WAIT_SEND(3, "等待发送"),

    SUCCESS_SEND(4, "发送成功"),

    FAIL_SEND(5, "发送失败"),

    DELETE(6, "已删除")

    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;
}

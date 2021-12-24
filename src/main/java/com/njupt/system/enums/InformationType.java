package com.njupt.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:40 AM
 * @signature Do it while you can!
 */
@ToString
@AllArgsConstructor
public enum InformationType {

    PUBLIC_INFORMATION(0, "公共信息"),

    COURSE_COMPETITION(1, "学科竞赛"),

    INTERNATIONAL_COMMUNICATION(2, "国际交流"),

    ANNOUNCEMENT(3, "通知公告"),

    TEST_NOTIFICATION(4, "考试通知"),

    IMPORTANT_NEWS(5, "校内要闻"),

    HOT_ISSUES(6, "热点事件"),
    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;
}

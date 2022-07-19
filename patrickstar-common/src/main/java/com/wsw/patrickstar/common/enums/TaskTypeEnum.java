package com.wsw.patrickstar.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 15:52
 */
@Getter
public enum TaskTypeEnum {
    PRODUCT("生产", 1),
    TEST("测试", 2),
    CHECKOUTSIDE("稽核", 3),
    ;

    private final String des;
    private final Integer code;

    TaskTypeEnum(String des, Integer code) {
        this.des = des;
        this.code = code;
    }
}

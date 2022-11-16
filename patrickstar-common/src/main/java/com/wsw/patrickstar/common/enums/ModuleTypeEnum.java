package com.wsw.patrickstar.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:22
 */
@Getter
public enum ModuleTypeEnum {
    TASK("任务", 1),
    ;

    private final String des;
    private final Integer code;

    ModuleTypeEnum(String des, Integer code) {
        this.des = des;
        this.code = code;
    }
}

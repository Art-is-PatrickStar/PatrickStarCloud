package com.wsw.patrickstar.common.enums;

import lombok.Getter;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 15:55
 */
@Getter
public enum TaskStatusEnum {
    TODO("待处理", 1),
    DOING("处理中", 2),
    DONE("处理完成", 3),
    ;

    private final String des;
    private final Integer code;

    TaskStatusEnum(String des, Integer code) {
        this.des = des;
        this.code = code;
    }
}

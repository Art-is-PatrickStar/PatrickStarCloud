package com.wsw.patrickstar.common.enums;

import lombok.Getter;

/**
 * @Author WangSongWen
 * @Date: Created in 15:18 2021/1/25
 * @Description:
 */
@Getter
public enum OperationTypeEnum {
    ADD("ADD", "新增"),
    DELETE("DELETE", "删除"),
    UPDATE("UPDATE", "修改");

    private final String operation;
    private final String message;

    OperationTypeEnum(String operation, String message) {
        this.operation = operation;
        this.message = message;
    }
}

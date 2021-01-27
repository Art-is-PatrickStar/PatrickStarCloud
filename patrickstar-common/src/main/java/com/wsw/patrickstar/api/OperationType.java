package com.wsw.mysqlelasticsearch.api;

/**
 * @Author WangSongWen
 * @Date: Created in 15:18 2021/1/25
 * @Description:
 */
public enum OperationType {
    ADD("ADD", "新增"),
    DELETE("DELETE", "删除"),
    UPDATE("UPDATE", "修改");

    private final String operation;
    private final String message;

    OperationType(String operation, String message) {
        this.operation = operation;
        this.message = message;
    }

    public String getOperation() {
        return operation;
    }

    public String getMessage() {
        return message;
    }
}

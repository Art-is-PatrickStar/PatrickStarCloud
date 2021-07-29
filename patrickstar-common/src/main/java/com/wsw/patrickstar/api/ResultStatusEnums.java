package com.wsw.patrickstar.api;

/**
 * @Author WangSongWen
 * @Date: Created in 15:04 2020/11/11
 * @Description: 枚举一些常用的API操作码
 */
public enum ResultStatusEnums {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private final Integer status;
    private final String msg;

    ResultStatusEnums(Integer status, String message) {
        this.status = status;
        this.msg = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultStatusEnums getByStatus(Integer status) {
        for (ResultStatusEnums result : ResultStatusEnums.values()) {
            if (result.getStatus().equals(status)) {
                return result;
            }
        }
        return null;
    }
}

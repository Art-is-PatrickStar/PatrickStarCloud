package com.wsw.patrickstar.api.response;

/**
 * @Author WangSongWen
 * @Date: Created in 15:04 2020/11/11
 * @Description: 枚举一些常用的API操作码
 */
public enum ResultStatusEnums {
    SUCCESS(200, "操作成功", 0),
    FAILED(500, "操作失败", 0),
    VALIDATE_FAILED(404, "参数检验失败", 0),
    UNAUTHORIZED(401, "暂未登录或token已经过期", 0),
    FORBIDDEN(403, "没有相关权限", 0),

    OP_LOG_SAVE_FAILD(1000, "操作日志插入失败", 0),
    ;

    private final Integer status;
    private final String msg;
    private final Integer alert; //alert: 1 告警 0 不告警

    ResultStatusEnums(Integer status, String msg, Integer alert) {
        this.status = status;
        this.msg = msg;
        this.alert = alert;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getAlert() {
        return alert;
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

package com.wsw.patrickstar.common.exception;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 14:00 2021/2/4
 * @Description: patrickstar-task-service 自定义异常类
 */
public class CloudServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6094159908069439356L;

    private Throwable cause;

    public CloudServiceException(String message) {
        super(message);
    }

    public CloudServiceException(Throwable cause) {
        super(cause.getCause());
        this.cause = cause;
    }

    public CloudServiceException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

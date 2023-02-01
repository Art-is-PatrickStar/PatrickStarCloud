package com.wsw.patrickstar.aconcurrent.exception;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 16:10 2021/3/9
 * @Description:
 */
public class ServiceException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private Throwable cause;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public ServiceException(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

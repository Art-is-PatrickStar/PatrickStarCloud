package com.wsw.patrickstar.exception;

import com.wsw.patrickstar.api.IErrorCode;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 14:00 2021/2/4
 * @Description: patrickstar-task-service 自定义异常类
 */
public class TaskServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private IErrorCode errorCode;

    public TaskServiceException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public TaskServiceException(String message) {
        super(message);
    }

    public TaskServiceException(Throwable cause) {
        super(cause);
    }

    public TaskServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

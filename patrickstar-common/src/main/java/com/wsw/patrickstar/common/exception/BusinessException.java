package com.wsw.patrickstar.common.exception;

import com.wsw.patrickstar.api.response.ResultStatusEnums;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 14:00 2021/2/4
 * @Description: 自定义异常类
 */
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6094159908069439356L;

    private final String message;
    private final ResultStatusEnums resultStatusEnums;

    public BusinessException() {
        this(ResultStatusEnums.SYSTEM_EXCEPTION);
    }

    public BusinessException(ResultStatusEnums resultStatusEnums) {
        this(resultStatusEnums, resultStatusEnums.getMsg());
    }

    public BusinessException(ResultStatusEnums resultStatusEnums, String message) {
        this(resultStatusEnums, message, null);
    }

    public BusinessException(ResultStatusEnums resultStatusEnums, Throwable cause) {
        this(resultStatusEnums, resultStatusEnums.getMsg(), cause);
    }

    public BusinessException(ResultStatusEnums resultStatusEnums, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.resultStatusEnums = resultStatusEnums;
    }

    public String getMessage() {
        return message;
    }

    public ResultStatusEnums getResultStatusEnums() {
        return resultStatusEnums;
    }
}

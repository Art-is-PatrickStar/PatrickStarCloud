package com.wsw.patrickstar.common.exception;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/15 10:10
 */
public class LockAcquireFailException extends RuntimeException {
    private static final long serialVersionUID = 8454539792991583176L;

    public LockAcquireFailException(String message) {
        super(message);
    }
}

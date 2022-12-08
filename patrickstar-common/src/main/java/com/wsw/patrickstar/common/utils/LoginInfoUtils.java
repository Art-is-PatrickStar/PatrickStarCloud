package com.wsw.patrickstar.common.utils;

import com.wsw.patrickstar.api.model.dto.base.LoginInfo;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/8 17:18
 */
public class LoginInfoUtils {
    public static ThreadLocal<LoginInfo> loginInfoThreadLocal = new ThreadLocal<>();

    public static LoginInfo getCurrentThreadLoginInfo() {
        try {
            return loginInfoThreadLocal.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static void putCurrentThreadLoginInfo(LoginInfo loginInfo) {
        loginInfoThreadLocal.set(loginInfo);
    }

    public static void clearThreadLoginInfo() {
        loginInfoThreadLocal.remove();
    }

    public static void putCurrentThreadLoginInfo(String userId, String userName) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(userId);
        loginInfo.setUserName(userName);
        loginInfoThreadLocal.set(loginInfo);
    }
}

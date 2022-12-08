package com.wsw.patrickstar.task.interceptor;

import cn.hutool.core.util.StrUtil;
import com.wsw.patrickstar.api.model.dto.base.LoginInfo;
import com.wsw.patrickstar.common.utils.LoginInfoUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/8 18:03
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        LoginInfo loginInfo = new LoginInfo();
        String userId = request.getHeader("Authorization-UserId");
        String userName = request.getHeader("Authorization-UserName");
        loginInfo.setUserId(StrUtil.isNotBlank(userId) ? userId : "");
        loginInfo.setUserName(StrUtil.isNotBlank(userName) ? userName : "");
        LoginInfoUtils.putCurrentThreadLoginInfo(loginInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginInfoUtils.clearThreadLoginInfo();
    }
}
package com.wsw.patrickstar.aconcurrent.security;

import com.wsw.patrickstar.aconcurrent.model.Request;
import com.wsw.patrickstar.aconcurrent.utils.SecurityUtil;

/**
 * @Author WangSongWen
 * @Date: Created in 14:23 2021/3/15
 * @Description:
 */
public class Desensitization {
    public static void main(String[] args) {
        Request request = new Request();
        request.setName("王松文");
        request.setPhone("12345678909");
        request.setIdCard("432156788909084123");
        request.setImg("img2021");
        SecurityUtil securityUtil = new SecurityUtil();
        String result = securityUtil.toJsonString(request);
        System.out.println(result);
    }
}

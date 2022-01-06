package com.wsw.patrickstar.auth.service;

import com.wsw.patrickstar.api.domain.User;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
public interface AuthService {
    User auth(String username, String password);
}

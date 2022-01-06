package com.wsw.patrickstar.auth.service.impl;

import com.wsw.patrickstar.api.domain.User;
import com.wsw.patrickstar.auth.service.AuthService;
import com.wsw.patrickstar.auth.repository.AuthRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthRepository authRepository;

    @Override
    public User auth(String username, String password) {
        User user = authRepository.findUserByUsernameAndPassword(username, password);
        if (null == user){
            throw new SecurityException("用户名或密码错误!");
        }
        return user;
    }
}

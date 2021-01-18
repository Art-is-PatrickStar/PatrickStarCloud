package com.wsw.patrickstarservice.service.impl;

import com.wsw.patrickstar.domain.User;
import com.wsw.patrickstarservice.repository.AuthRepository;
import com.wsw.patrickstarservice.service.AuthService;
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

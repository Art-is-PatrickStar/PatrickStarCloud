package com.wsw.patrickstar.auth.service;

import com.wsw.patrickstar.api.model.dto.UserLoginDTO;
import com.wsw.patrickstar.api.model.dto.UserSignUpDTO;
import com.wsw.patrickstar.auth.entity.User;
import com.wsw.patrickstar.auth.mapstruct.IUserConvert;
import com.wsw.patrickstar.auth.repository.AuthRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
@Service
public class AuthService {
    @Resource
    private AuthRepository authRepository;

    public User login(UserLoginDTO userLoginDTO) {
        return authRepository.findUserByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    public boolean ifUserExist(UserSignUpDTO userSignUpDTO) {
        List<User> users = authRepository.findUserByUsername(userSignUpDTO.getUsername());
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    public void signup(UserSignUpDTO userSignUpDTO) {
        authRepository.save(IUserConvert.INSTANCE.DtoToEntity(userSignUpDTO));
    }
}

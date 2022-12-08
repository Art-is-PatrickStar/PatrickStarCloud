package com.wsw.patrickstar.auth.service;

import cn.hutool.core.util.StrUtil;
import com.wsw.patrickstar.api.model.dto.UserLoginDTO;
import com.wsw.patrickstar.api.model.dto.UserSignUpDTO;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.auth.entity.User;
import com.wsw.patrickstar.auth.mapstruct.IUserConvert;
import com.wsw.patrickstar.auth.repository.AuthRepository;
import com.wsw.patrickstar.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
@Slf4j
@Service
public class AuthService {
    @Resource
    private AuthRepository authRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    public User login(UserLoginDTO userLoginDTO) {
        User user = authRepository.findUserByUsername(userLoginDTO.getUsername());
        if (!Objects.isNull(user) && StrUtil.isNotBlank(user.getPassword())) {
            boolean matches = passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
            if (!matches) {
                throw new BusinessException(ResultStatusEnums.USER_INFORMATION_ERROR);
            }
        }
        return user;
    }

    public boolean ifUserExist(UserSignUpDTO userSignUpDTO) {
        List<User> users = authRepository.findUsersByUsername(userSignUpDTO.getUsername());
        return users.size() > 0;
    }

    public void signup(UserSignUpDTO userSignUpDTO) {
        userSignUpDTO.setPassword(passwordEncoder.encode(userSignUpDTO.getPassword()));
        authRepository.save(IUserConvert.INSTANCE.DtoToEntity(userSignUpDTO));
    }
}

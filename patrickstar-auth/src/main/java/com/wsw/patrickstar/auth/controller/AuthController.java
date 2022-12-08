package com.wsw.patrickstar.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.wsw.patrickstar.api.model.dto.UserAuthResponseDTO;
import com.wsw.patrickstar.api.model.dto.UserLoginDTO;
import com.wsw.patrickstar.api.model.dto.UserSignUpDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.auth.entity.User;
import com.wsw.patrickstar.auth.service.AuthService;
import com.wsw.patrickstar.auth.utils.JwtRedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author WangSongWen
 * @Date: Created in 17:47 2020/11/12
 * @Description: 认证中心颁发给前端token与refresh_token
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthService authService;
    @Resource
    private JwtRedisUtils jwtRedisUtils;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<UserAuthResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        Result<UserAuthResponseDTO> result = Result.createSuccessResult();
        User user = authService.login(userLoginDTO);
        if (Objects.isNull(user)) {
            return Result.createFailResult(ResultStatusEnums.USER_NOT_FOUND);
        }
        UserAuthResponseDTO userAuthResponseDTO = jwtRedisUtils.generateTokenAndRefreshToken(user.getUserId(), user.getUsername());
        result.setData(userAuthResponseDTO);
        return result;
    }

    @ApiOperation("用户注册")
    @PostMapping("/signup")
    public Result<Void> signup(@RequestBody UserSignUpDTO userSignUpDTO) {
        if (authService.ifUserExist(userSignUpDTO)) {
            return Result.createFailResult(ResultStatusEnums.USER_IS_EXIST);
        }
        authService.signup(userSignUpDTO);
        return Result.createSuccessResult();
    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestParam String userName) {
        jwtRedisUtils.removeToken(userName);
        return Result.createSuccessResult();
    }

    @ApiOperation("刷新token")
    @GetMapping("/refreshToken")
    public Result<UserAuthResponseDTO> refreshToken(@RequestParam String refreshToken) {
        Result<UserAuthResponseDTO> result = Result.createSuccessResult();
        Boolean tokenExpired = jwtRedisUtils.isTokenExpired(refreshToken);
        if (tokenExpired) {
            return Result.createFailResult(ResultStatusEnums.TOKEN_INVALID);
        }
        Boolean refreshTokenNotExistCache = jwtRedisUtils.isRefreshTokenNotExistCache(refreshToken);
        if (refreshTokenNotExistCache) {
            return Result.createFailResult(ResultStatusEnums.TOKEN_INVALID);
        }
        Long userId = jwtRedisUtils.getUserIdFromToken(refreshToken);
        String userName = jwtRedisUtils.getUserNameFromToken(refreshToken);
        if (Objects.isNull(userId) || StrUtil.isBlank(userName)) {
            return Result.createFailResult(ResultStatusEnums.PARAMS_EXCEPTION);
        }
        UserAuthResponseDTO userAuthResponseDTO = jwtRedisUtils.generateTokenAndRefreshToken(userId, userName);
        result.setData(userAuthResponseDTO);
        return result;
    }
}

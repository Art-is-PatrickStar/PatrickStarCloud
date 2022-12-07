package com.wsw.patrickstar.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wsw.patrickstar.api.model.dto.UserAuthResponseDTO;
import com.wsw.patrickstar.api.model.dto.UserLoginDTO;
import com.wsw.patrickstar.api.model.dto.UserSignUpDTO;
import com.wsw.patrickstar.api.response.Result;
import com.wsw.patrickstar.api.response.ResultStatusEnums;
import com.wsw.patrickstar.auth.entity.User;
import com.wsw.patrickstar.auth.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date: Created in 17:47 2020/11/12
 * @Description: 认证中心颁发给前端token与refresh_token
 */
@Slf4j
@RestController
public class AuthController {
    @Resource
    private AuthService authService;

    @Value("${jwt.secretKey}")
    private String secretKey; // token密钥

    @Value("${token.expire.time}")
    private long tokenExpireTime;

    @Value("${refresh.token.expire.time}")
    private long refreshTokenExpireTime;

    @Value("${jwt.refresh.token.key.format}")
    private String jwtRefreshTokenKeyFormat;

    @Value("${jwt.blacklist.key.format}")
    private String jwtBlacklistKeyFormat;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<UserAuthResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        Result<UserAuthResponseDTO> result = Result.createSuccessResult();
        User user = authService.login(userLoginDTO);
        if (Objects.isNull(user)) {
            return Result.createFailResult(ResultStatusEnums.USER_NOT_FOUND);
        }
        // 生成token
        String token = buildJWT(user.getUsername());
        // 生成refreshToken
        String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
        // 保存refreshToken至redis，使用hash结构保存使用中的token以及用户标识
        String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
        stringRedisTemplate.opsForHash().put(refreshTokenKey, "token", token);
        stringRedisTemplate.opsForHash().put(refreshTokenKey, "username", user.getUsername());
        // refreshToken设置过期时间
        stringRedisTemplate.expire(refreshTokenKey, refreshTokenExpireTime, TimeUnit.HOURS);
        // 返回结果
        UserAuthResponseDTO userAuthResponseDTO = new UserAuthResponseDTO();
        userAuthResponseDTO.setUserId(user.getUserId());
        userAuthResponseDTO.setUsername(user.getUsername());
        userAuthResponseDTO.setToken(token);
        userAuthResponseDTO.setRefreshToken(refreshToken);
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

    @ApiOperation("刷新token")
    @GetMapping("/refreshToken")
    public Result<Map<String, Object>> refreshToken(@RequestParam String refreshToken) {
        Result<Map<String, Object>> result = Result.createSuccessResult();
        Map<String, Object> resultMap = new HashMap<>();
        String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
        String userName = (String) stringRedisTemplate.opsForHash().get(refreshTokenKey, "username");
        if (StringUtils.isBlank(userName)) {
            result = Result.createFailResult(ResultStatusEnums.UNAUTHORIZED);
            return result;
        }
        String newToken = buildJWT(userName);
        // 替换当前token，并将旧token添加到黑名单
        String oldToken = (String) stringRedisTemplate.opsForHash().get(refreshTokenKey, "token");
        stringRedisTemplate.opsForHash().put(refreshTokenKey, "token", newToken);
        stringRedisTemplate.opsForValue().set(String.format(jwtBlacklistKeyFormat, oldToken), "",
                tokenExpireTime, TimeUnit.HOURS);
        resultMap.put("token", newToken);
        result.setData(resultMap);
        return result;
    }

    private String buildJWT(String userName) {
        //生成jwt
        Date now = new Date();
        Algorithm algo = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(userName)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("userName", userName) // 保存身份标识
                .sign(algo);
    }
}

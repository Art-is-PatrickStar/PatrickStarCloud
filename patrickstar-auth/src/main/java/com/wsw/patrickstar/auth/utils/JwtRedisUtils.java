package com.wsw.patrickstar.auth.utils;

import com.wsw.patrickstar.api.model.dto.UserAuthResponseDTO;
import com.wsw.patrickstar.common.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/8 16:46
 */
@Component
public class JwtRedisUtils extends JwtUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public UserAuthResponseDTO generateTokenAndRefreshToken(Long userId, String userName) {
        UserAuthResponseDTO userAuthResponseDTO = new UserAuthResponseDTO();
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId", userId);
        claimsMap.put("userName", userName);
        // 生成accessToken
        String accessToken = getAccessToken(claimsMap);
        // 生成refreshToken
        String refreshToken = getRefreshToken(claimsMap);
        // 存储token到redis缓存
        cacheToken(userName, accessToken, refreshToken);
        userAuthResponseDTO.setUserId(userId);
        userAuthResponseDTO.setUsername(userName);
        userAuthResponseDTO.setAccessToken(accessToken);
        userAuthResponseDTO.setRefreshToken(refreshToken);
        return userAuthResponseDTO;
    }

    public void cacheToken(String userName, String accessToken, String refreshToken) {
        stringRedisTemplate.opsForHash().put(JWT_CACHE_KEY + userName, ACCESS_TOKEN, accessToken);
        stringRedisTemplate.opsForHash().put(JWT_CACHE_KEY + userName, REFRESH_TOKEN, refreshToken);
        stringRedisTemplate.expire(userName, getRefreshTokenExpireTime(), TimeUnit.MILLISECONDS);
    }

    public void removeToken(String userName) {
        stringRedisTemplate.delete(JWT_CACHE_KEY + userName);
    }

    public Boolean isRefreshTokenNotExistCache(String token) {
        String userName = getUserNameFromToken(token);
        String refreshToken = (String) stringRedisTemplate.opsForHash().get(JWT_CACHE_KEY + userName, REFRESH_TOKEN);
        return refreshToken == null || !refreshToken.equals(token);
    }
}

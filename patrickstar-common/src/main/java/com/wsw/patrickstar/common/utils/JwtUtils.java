package com.wsw.patrickstar.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/8 16:44
 */
@Component
public class JwtUtils {
    public static final String JWT_CACHE_KEY = "jwt:username:";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String ISSUER = "patrickstar-auth-root";

    @Value("${jwt.secretKey}")
    private String secretKey; // token密钥
    @Value("${jwt.access.token.expire.time}")
    private long accessTokenExpireTime;
    @Value("${jwt.refresh.token.expire.time}")
    private long refreshTokenExpireTime;

    public String getAccessToken(Map<String, Object> claimsMap) {
        return buildJwt(claimsMap, accessTokenExpireTime);
    }

    public String getRefreshToken(Map<String, Object> claimsMap) {
        return buildJwt(claimsMap, refreshTokenExpireTime);
    }

    public String buildJwt(Map<String, Object> claims, long tokenExpireTime) {
        Date expirationDate = new Date(System.currentTimeMillis() + tokenExpireTime);
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Long getUserIdFromToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return Long.parseLong(claimsFromToken.get("userId").toString());
    }

    public String getUserNameFromToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return (String) claimsFromToken.get("userName");
    }

    public String getSecretKey() {
        return secretKey;
    }

    public long getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public long getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }
}

package com.wsw.summercloud.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wsw.summercloud.config.AuthConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author WangSongWen
 * @Date: Created in 10:51 2021/1/14
 * @Description:
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    private AuthConfig authConfig;

    @Value("${auth.skip.urls}")
    private String[] skipAuthUrls;

    @Value("${jwt.blacklist.key.format}")
    private String jwtBlacklistKeyFormat;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if(Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }
        // 从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 未携带token或token在黑名单内
        if (token == null || token.isEmpty() || isBlackToken(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"401\",\"massage\": \"401 Unauthorized.\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        // 取出token包含的身份，用于业务处理
        String userName = verifyJWT(token);
        if(userName.isEmpty()){
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"403\",\"msg\": \"invalid token.\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        // 将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header("Authorization-UserName", userName).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    /**
     * @description: JWT验证
     * @author: wangsongwen
     * @date: 2021/1/14 11:05
     **/
    private String verifyJWT(String token){
        String userName;
        String secretKey = authConfig.getSecretKey();  // token密钥
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("WSW")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            userName = jwt.getClaim("userName").asString();
        } catch (JWTVerificationException e){
            log.error(e.getMessage(), e);
            return "";
        }
        return userName;
    }

    /**
     * @description: 判断token是否在黑名单内
     * @author: wangsongwen
     * @date: 2021/1/14 11:05
     **/
    private boolean isBlackToken(String token){
        Boolean hasKey = stringRedisTemplate.hasKey(String.format(jwtBlacklistKeyFormat, token));
        return hasKey;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

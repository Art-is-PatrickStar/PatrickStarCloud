package com.wsw.patrickstar.gateway.filters;

import cn.hutool.core.util.StrUtil;
import com.wsw.patrickstar.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
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
import java.util.Objects;

/**
 * @Author WangSongWen
 * @Date: Created in 10:51 2021/1/14
 * @Description:
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Value("${auth.skip.urls}")
    private String[] skipAuthUrls;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (Arrays.asList(skipAuthUrls).contains(url)) {
            return chain.filter(exchange);
        }
        // 从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 未携带token
        if (StrUtil.isBlank(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"401\",\"massage\": \"未携带token\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        // 校验token是否过期
        if (jwtUtils.isTokenExpired(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"402\",\"massage\": \"token已过期\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        // 取出token包含的身份，用于业务处理
        Long userId = jwtUtils.getUserIdFromToken(token);
        String userName = jwtUtils.getUserNameFromToken(token);
        if (Objects.isNull(userId) || StrUtil.isBlank(userName)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"403\",\"massage\": \"token无效\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        // 将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                .header("Authorization-UserId", String.valueOf(userId))
                .header("Authorization-UserName", userName)
                .build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

package com.wsw.patrickstarservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date 2020/11/15 下午6:36
 * @Description:
 */
@Setter
@Getter
@Configuration
@RefreshScope
public class AuthConfig {
    @Value("${jwt.secretKey}")
    private String secretKey;
}

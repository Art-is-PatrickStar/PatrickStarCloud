package com.wsw.patrickstar.aconcurrent.fastdfs.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author WangSongWen
 * @Date: Created in 16:58 2020/12/28
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "fdfs")
public class FastDFSConfig {
    @Getter
    private Integer fastdfsConnectTimeout;

    private Integer fastdfsSoTimeout;

    private String fastdfsTrackerList;
}

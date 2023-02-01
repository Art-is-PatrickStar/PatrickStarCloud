package com.wsw.patrickstar.aconcurrent.service;

import com.wsw.patrickstar.aconcurrent.fastdfs.config.FastDFSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangsongwen
 * @Date: 2021/9/13 15:13
 * @Description: 类初始化顺序test
 */
@Service
public class FastDFSTestService {
    private final FastDFSConfig fastDFSConfig;

    private final Integer fastdfsConnectTimeout;

    @Autowired
    public FastDFSTestService(FastDFSConfig fastDFSConfig) {
        this.fastDFSConfig = fastDFSConfig;
        this.fastdfsConnectTimeout = fastDFSConfig.getFastdfsConnectTimeout();
    }

    public void say() {
        System.out.println(fastdfsConnectTimeout);
        System.out.println(fastDFSConfig.getFastdfsConnectTimeout());
    }
}

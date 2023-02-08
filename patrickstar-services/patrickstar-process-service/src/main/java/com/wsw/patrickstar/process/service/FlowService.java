package com.wsw.patrickstar.process.service;

/**
 * @Description: 流程相关接口
 * @Author: wangsongwen
 * @Date: 2023/2/8 16:55
 */
public interface FlowService {
    /**
     * 部署流程
     *
     * @param name
     * @param resource
     * @return void
     */
    void deployProcess(String name, String resource);

    /**
     * 开启流程
     *
     * @param processKey
     * @param businessKey
     * @return void
     */
    void startProcess(String processKey, String businessKey);
}

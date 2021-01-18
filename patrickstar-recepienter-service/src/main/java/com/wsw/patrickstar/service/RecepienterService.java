package com.wsw.patrickstarrecepienterservice.service;


/**
 * @Author WangSongWen
 * @Date: Created in 13:44 2020/11/20
 * @Description:
 */
public interface RecepienterService {
    /**
     * 新增领取者
     * @param name
     * @param remark
     * @return
     */
    int insert(Long taskId, String taskName, String name, String remark);
}

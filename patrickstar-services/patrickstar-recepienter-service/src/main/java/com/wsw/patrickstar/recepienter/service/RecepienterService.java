package com.wsw.patrickstar.recepienter.service;


import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;

/**
 * @Author WangSongWen
 * @Date: Created in 13:44 2020/11/20
 * @Description:
 */
public interface RecepienterService {
    /**
     * @description: 新增任务记录
     * @author: wangsongwen
     * @date: 2022/7/19 13:19
     * @param: [taskRecordDTO]
     * @return: void
     */
    void createTaskRecord(TaskRecordDTO taskRecordDTO);
}

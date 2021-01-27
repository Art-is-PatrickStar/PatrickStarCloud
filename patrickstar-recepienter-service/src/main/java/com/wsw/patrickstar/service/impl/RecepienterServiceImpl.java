package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.repository.RecepienterRepository;
import com.wsw.patrickstar.service.RecepienterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Service
@Slf4j
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterRepository recepienterRepository;

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return recepienterRepository.save(taskId, taskName, name, remark);
    }
}

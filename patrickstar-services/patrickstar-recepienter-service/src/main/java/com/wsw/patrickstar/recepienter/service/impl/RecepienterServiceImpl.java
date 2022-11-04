package com.wsw.patrickstar.recepienter.service.impl;

import com.wsw.patrickstar.api.model.dto.TaskRecordDTO;
import com.wsw.patrickstar.recepienter.entity.TaskRecordEntity;
import com.wsw.patrickstar.recepienter.mapstruct.IRecepienterConvert;
import com.wsw.patrickstar.recepienter.repository.RecepienterRepository;
import com.wsw.patrickstar.recepienter.service.RecepienterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Slf4j
@Service
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterRepository recepienterRepository;

    @Override
    public void createTaskRecord(TaskRecordDTO taskRecordDTO) {
        TaskRecordEntity taskRecordEntity = IRecepienterConvert.INSTANCE.dtoToEntity(taskRecordDTO);
        taskRecordEntity.setCreateTime(new Date());
        taskRecordEntity.setUpdateTime(new Date());
        recepienterRepository.save(taskRecordEntity);
    }
}

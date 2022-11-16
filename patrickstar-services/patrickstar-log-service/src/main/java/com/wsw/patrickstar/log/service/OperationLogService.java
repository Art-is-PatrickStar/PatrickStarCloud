package com.wsw.patrickstar.log.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.log.entity.OperationLogEntity;
import com.wsw.patrickstar.log.mapper.OperationLogMapper;
import com.wsw.patrickstar.log.mapstruct.IOperationLogConvert;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:57
 */
@Service
public class OperationLogService extends ServiceImpl<OperationLogMapper, OperationLogEntity> {
    public void saveLog(OpLogDTO opLogDTO) {
        OperationLogEntity operationLogEntity = IOperationLogConvert.INSTANCE.dtoToEntity(opLogDTO);
        this.save(operationLogEntity);
    }

    public List<OpLogDTO> getOperationLogs(String moduleId) {
        List<OperationLogEntity> logEntities = this.lambdaQuery().eq(OperationLogEntity::getModuleId, moduleId).list();
        return IOperationLogConvert.INSTANCE.entityToDto(logEntities);
    }
}

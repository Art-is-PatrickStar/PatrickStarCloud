package com.wsw.patrickstar.task.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsw.patrickstar.api.model.dto.OpLogDTO;
import com.wsw.patrickstar.common.annotation.OpLog;
import com.wsw.patrickstar.common.enums.OperationType;
import com.wsw.patrickstar.common.utils.CompareUtils;
import com.wsw.patrickstar.common.utils.SpringUtils;
import com.wsw.patrickstar.task.service.TaskOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:37
 */
@Slf4j
@Aspect
@Component
public class OpLogAspect {
    @Resource
    private TaskOperationLogService taskOperationLogService;

    @Pointcut("@annotation(com.wsw.patrickstar.common.annotation.OpLog)")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object setOpLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            //通过连接点获取方法签名 被切入方法的所有信息
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            //获取被切入方法对象
            Method method = signature.getMethod();
            //获取方法上的注解
            OpLog opLog = method.getAnnotation(OpLog.class);
            //操作日志类
            OpLogDTO opLogDTO = new OpLogDTO();
            opLogDTO.setOperateType(opLog.opType().getMessage());
            opLogDTO.setModuleType(opLog.type().getDes());
            if (opLog.opType() == OperationType.UPDATE) {
                Object oldValue = getResult(pjp, opLog);
                result = pjp.proceed();
                Object newValue = getResult(pjp, opLog);
                //过滤忽略字段，比较实体的变化字段
                String operateContent = CompareUtils.compareFields(oldValue, newValue, opLog.ignoreFields());
                if (StringUtils.isEmpty(operateContent)) {
                    operateContent = "无变化";
                }
                opLogDTO.setOperateContent(operateContent);
            } else if (opLog.opType() == OperationType.ADD || opLog.opType() == OperationType.DELETE) {
                result = pjp.proceed();
                opLogDTO.setOperateContent(opLog.opType().getMessage() + opLog.type().getDes());
            }
            //操作人员

            Object paramValue = getParamValue(pjp, opLog);
            opLogDTO.setModuleId(String.valueOf(paramValue));
            taskOperationLogService.saveLog(opLogDTO);
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private Object getResult(ProceedingJoinPoint pjp, OpLog opLog) {
        try {
            Object[] args = pjp.getArgs();
            if (args.length > 0) {
                Object arg = args[0];
                String params = JSONObject.toJSONString(arg);
                String idString = JSONPath.read(params, opLog.typeId()).toString();
                Long id = Long.valueOf(idString);
                Class<IService> iServiceClass = opLog.serviceClass();
                IService iService = SpringUtils.getBean(iServiceClass);
                return iService.getById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Object getParamValue(ProceedingJoinPoint pjp, OpLog opLog) {
        try {
            Object[] args = pjp.getArgs();
            if (args.length > 0) {
                Object arg = args[0];
                String params = JSONObject.toJSONString(arg);
                return JSONPath.read(params, opLog.typeId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

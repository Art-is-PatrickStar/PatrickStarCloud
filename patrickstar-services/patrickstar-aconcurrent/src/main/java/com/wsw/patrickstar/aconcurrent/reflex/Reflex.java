package com.wsw.patrickstar.aconcurrent.reflex;

import com.wsw.patrickstar.aconcurrent.exception.ServiceException;
import com.wsw.patrickstar.aconcurrent.service.ReflexServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2021/3/9
 * @Description: 反射
 */
@Slf4j
@Aspect
@Component
public class Reflex {

    @Pointcut("execution(* com.wsw.patrickstar.aconcurrent.service.ReflexServiceImpl.toString(..))")
    public void reflexTestPointCut() {
    }

    @Around("reflexTestPointCut()")
    public Object reflexTest(ProceedingJoinPoint joinPoint) throws ServiceException {
        Object object = null;
        ReflexServiceImpl reflexService = new ReflexServiceImpl();
        try {
            Object[] args = joinPoint.getArgs();
            String methodName = joinPoint.getSignature().getName();
            if (args != null && args.length > 0) {
                System.out.println(Arrays.toString(args) + " " + methodName);
                object = joinPoint.proceed(args);
                Method[] methods = reflexService.getClass().getMethods();
                for (Method method : methods) {
                    System.out.println(method.getName());
                    /*if (method.getName().equals("sayHello")) {
                        return method.invoke(reflexService, args);
                    }*/
                }
            }
        } catch (Throwable throwable) {
            throw new ServiceException(throwable.getMessage(), throwable);
        }
        return object;
    }

}

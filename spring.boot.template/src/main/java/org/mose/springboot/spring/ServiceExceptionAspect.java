package org.mose.springboot.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * service层异常拦截记录，用于捕捉Service层的所有异常信息，并记录日志
 *
 * @author 孔垂云
 */

@Aspect
@Component
public class ServiceExceptionAspect {
    private static Logger logger = LoggerFactory.getLogger("serviceLog");

    @AfterThrowing(value = "execution (* org.mose.springboot.*..service.*.*(..))", throwing = "e")
    public void loggingException(JoinPoint joinPoint, Exception e) {
        Object target = joinPoint.getTarget();  // 拦截的实体类
        String methodName = joinPoint.getSignature().getName();// 拦截的方法名称
        logger.error("实体类:" + target);
        logger.error("方法名:" + methodName);
        Object[] args = joinPoint.getArgs(); // 得到被拦截方法参数，并打印
        for (int i = 0; i < args.length; i++) {
            logger.error("被拦截到的方法参数：" + i + " -- " + args[i]);
        }
        logger.error("异常类名：" + e.getClass());
        logger.error("异常信息: " + e.getMessage());
    }
}

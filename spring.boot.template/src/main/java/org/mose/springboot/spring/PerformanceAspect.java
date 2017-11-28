package org.mose.springboot.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用于监控系统性能，service层性能有问题的记录下来
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Aspect
@Component
@ConfigurationProperties(prefix = "custom.performance")
public class PerformanceAspect {
    private static Logger logger = LoggerFactory.getLogger("performanceLog");
    private int checkPoint;

    @Around("execution (* org.mose.springboot.*..service.*.*(..))")
    public Object performanceIterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long l = System.currentTimeMillis();
        result = joinPoint.proceed();
        long consume = System.currentTimeMillis() - l;
        if (consume > checkPoint) {
            //记录系统操作较慢的service处理过程
            logger.info("实体类:" + joinPoint.getTarget());
            logger.info("方法名:" + joinPoint.getSignature().getName());
            // 得到被拦截方法参数，并打印
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                logger.info("方法参数：" + i + " -- " + args[i]);
            }
            logger.info("用时：" + consume);
        }
        return result;
    }

    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }
}

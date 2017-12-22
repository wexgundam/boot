package org.mose.boot.configuration.spring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.interceptor.*;

/**
 * what:    事务配置
 * <p>
 * spring boot 1.5.4自动依赖的aspectj 1.8.10冲突，如果部署报错请检查Maven依赖
 *
 * @Author: 靳磊
 * @Date: 2017/6/14:22
 */
@EnableTransactionManagement
@Aspect
@Component
public class TransactionConfiguration {
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* org.mose.springboot.*..service.*.*(..))";

    /**
     * spring事务管理器
     */
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * what:    事务处理谏言
     *
     * @return
     */
    @Bean
    public TransactionInterceptor transactionAdvice() {
        // 标记了@Transactional的方法会启动事务
        AnnotationTransactionAttributeSource annotationTransactionAttributeSource = new AnnotationTransactionAttributeSource(
                new SpringTransactionAnnotationParser());

        // 名称匹配的方法会启动事务
        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setReadOnly(true);
        nameMatchTransactionAttributeSource.addTransactionalMethod("*", transactionAttribute);

        CompositeTransactionAttributeSource source = new CompositeTransactionAttributeSource(
                new TransactionAttributeSource[]{annotationTransactionAttributeSource, nameMatchTransactionAttributeSource});
        TransactionInterceptor advice = new TransactionInterceptor(transactionManager, source);
        return advice;
    }

    /**
     * what:    织入
     *
     * @return
     */
    @Bean
    public Advisor transactionAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }
}

package com.jalivv.demo.helper.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description RedisAspect
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 10:36
 */
@Component
@Aspect
@Slf4j
public class RedisAspect {
    /**
     * 切面点
     */
    private final String POINT_CUT = "execution(* com.jalivv.demo.helper.controller..*.put(..))";

    @Pointcut(POINT_CUT)
    private void pointcut() {
    }


    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = "execution(* com.jalivv.demo.helper.controller..*.put(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        Object[] args = proceedingJoinPoint.getArgs();
        // 新增业务需求，如果 参数：插入的数据条数  小于50条，表示不合法，return error
        try {
            if ((long) args[0] < 50) {
                return "error：版本2.0升级之后，数据少于50，不准插入！";
            } else {
                long startTime = new Date().getTime();
                Object obj = proceedingJoinPoint.proceed();
                log.info("redis插入{}条数据耗时：{}ms", args[0], (new Date().getTime() - startTime));
                return obj;
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}

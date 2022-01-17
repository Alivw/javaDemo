package com.jalivv.demo.helper.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description RedisAspect
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 10:36
 */
//@Component
@Aspect
public class RedisAspect {
    /**
     * 切面点
     */
    private final String POINT_CUT = "execution(* com.jalivv.demo..*(..))";

    @Pointcut(POINT_CUT)
    private void pointcut() {
    }
}

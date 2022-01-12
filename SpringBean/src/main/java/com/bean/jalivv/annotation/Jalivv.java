package com.bean.jalivv.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 20:20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jalivv {

    String value() default "jalivv";
}

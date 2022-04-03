package com.jalivv.spring.a20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 经常需要用到请求头中的 token 信息，用下面注解来标注由哪个参数来获取它
 *  token=令牌
 * @Date 2022/4/3 16:33
 * @Created by jalivv
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

}

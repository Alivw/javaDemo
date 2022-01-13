package org.apache.ibatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 11:03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface JalivvMapperScan {
    String value();
}

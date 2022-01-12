package com.bean.jalivv.postprocessor;

import com.bean.jalivv.annotation.Jalivv;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 20:21
 */
@Component
public class BeanWithJalivvAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if ("student".equals(s)) {
            Class<?> clazz = o.getClass();
            try {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Jalivv.class)) {
                        field.setAccessible(true);
                        String value = field.getAnnotation(Jalivv.class).value();
                        field.set(o, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}

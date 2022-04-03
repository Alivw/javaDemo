package com.jalivv.spring.a20;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description
 * @Date 2022/4/3 16:38
 * @Created by jalivv
 */
public class TokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    // 支持哪些参数解析
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 由 token 注解，才解析
        return parameter.getParameterAnnotation(Token.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getHeader("token");
    }
}

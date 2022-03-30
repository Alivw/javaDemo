package com.jalivv.spring.a09;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2022/3/30 12:40
 * @Created by jalivv
 */
@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class F2 {
}

package com.jalivv.spring.a08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2022/3/30 12:12
 * @Created by jalivv
 */
@RestController
public class MyController {

    @Lazy
    @Autowired
    private Bean4Request bean4Request;

    @Lazy
    @Autowired
    private Bean4Session bean4Session;

    @Lazy
    @Autowired
    private Bean4Application bean4Application;

    @RequestMapping("/test")
    public String test() {

        return "<ul>" +
                "<li>" + "request scope:" + bean4Request + "</li>" +
                "<li>" + "session scope:" + bean4Session + "</li>" +
                "<li>" + "application scope:" + bean4Application + "</li>" +
                "</ul>";

    }
}

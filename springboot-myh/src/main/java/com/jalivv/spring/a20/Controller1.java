package com.jalivv.spring.a20;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @Date 2022/4/3 12:47
 * @Created by jalivv
 */
@Controller
public class Controller1 {

    private static final Logger logger = LoggerFactory.getLogger(Controller1.class);

    @GetMapping("/test1")
    public ModelAndView test1() {
        logger.debug("test1()");
        return null;
    }

    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name") String name) {
        logger.debug("test2(){}", name);
        return null;
    }

    @PutMapping("/test3")
    public ModelAndView test3(@Token String token) {

        logger.debug("test3(){}",token);
        return null;
    }

    @RequestMapping("/test4")
    public ModelAndView test4() {
        logger.debug("test4()");
        return null;
    }

}

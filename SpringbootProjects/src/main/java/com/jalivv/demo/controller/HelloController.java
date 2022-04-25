package com.jalivv.demo.controller;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description HelloController
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/22 14:22
 */
//@RestController
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/test1")
    public String hello1(
            Map<String, Object> user,
            Model model) {
        model.addAttribute("hello", "world666");
        user.put("world", "hello666");
        return "forward:/hello/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public Object success(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", request.getAttribute("hello"));
        map.put("world", request.getAttribute("world"));
        return map;
    }

    @GetMapping("abc")
    @ResponseBody
    public Object success1(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", request.getAttribute("hello"));
        map.put("world", request.getAttribute("world"));
        return map;
    }
}

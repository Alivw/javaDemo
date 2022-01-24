package com.jalivv.demo.controller;

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
}

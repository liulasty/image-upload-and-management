package com.lz.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2024/02/08/13:07
 * @Description:
 */

import com.lz.pojo.constant.Weekday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lz
 */
@Controller
@RequestMapping("/hello")
public class MyController {
    @GetMapping("/1")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }


    @GetMapping("/2")
    public String hello1(Model model) {
        model.addAttribute("Weekdays", Weekday.values());
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index";
    }

    @GetMapping("/3")
    public String hello2(Model model) {
        
        return "index_vue";
    }
}
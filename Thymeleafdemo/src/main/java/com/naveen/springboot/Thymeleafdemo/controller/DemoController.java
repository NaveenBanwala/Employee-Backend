package com.naveen.springboot.Thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("date", LocalDateTime.now());
        return "helloWorld"; // This will look for helloWorld.html in src/main/resources/templates
    }
}

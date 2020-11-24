package com.example.boot04.boot04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}

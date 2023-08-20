package com.midas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author midas
 * 测试controller
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("")
    public String home() {
        return "Hello World!";
    }
}

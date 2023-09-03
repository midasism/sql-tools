package com.midas.controller;

import com.midas.domain.MySQLResult;
import com.midas.service.JsonService;
import jakarta.annotation.Resource;
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

    @Resource
    private JsonService jsonService;

    @GetMapping("")
    public MySQLResult home(String jsonStr) {
        return jsonService.generateSQLByJSON(jsonStr);
    }
}

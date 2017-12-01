package com.example.demosecurity4.controller;

import com.example.demosecurity4.test.entity.Test;
import com.example.demosecurity4.test.service.TestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Li Hongxing
 * @description: 测试controller
 * @date: Created in 2017/12/02 0:40
 * @modified:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource private TestService testService;

    @RequestMapping("/{id}")
    public Test getById(@PathVariable Integer id){
        Test test = testService.selectById(id);
        return test != null ? test : new Test();
    }
}

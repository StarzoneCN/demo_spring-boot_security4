package com.example.demosecurity4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Li Hongxing
 * @description: index
 * @date: Created in 2017/12/01 14:17
 * @modified:
 */
@RestController
@RequestMapping
public class IndexController {

    @RequestMapping
    public String index(){
        return "welcome to call index method";
    }
}

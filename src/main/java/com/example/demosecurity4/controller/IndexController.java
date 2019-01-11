package com.example.demosecurity4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String index(HttpServletRequest request){
        return "welcome to call index method";
    }
}

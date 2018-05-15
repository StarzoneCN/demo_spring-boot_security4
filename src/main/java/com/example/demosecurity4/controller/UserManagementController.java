package com.example.demosecurity4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author: Li Hongxing
 * @description: 用户管理接口
 * @date: Created in 2017/12/01 23:46
 * @modified:
 */
@RestController
@RequestMapping("/user/manage")
/*hasAuthority等同hasRole*/
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserManagementController {

    @RequestMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String index(Principal user){
        return "thanks for calling user management index method";
    }
}

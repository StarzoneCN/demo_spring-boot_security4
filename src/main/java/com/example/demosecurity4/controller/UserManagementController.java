package com.example.demosecurity4.controller;

import com.example.demosecurity4.user.entity.Users;
import com.example.demosecurity4.user.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired private IUsersService usersService;

    @RequestMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String index(Principal user){
        return "thanks for calling user management index method";
    }

    @GetMapping("info/{id}")
    public Users getById(@PathVariable Integer id){
        return usersService.selectById(id);
    }
}

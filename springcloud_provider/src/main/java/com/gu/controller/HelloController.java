package com.gu.controller;

import com.gu.pojo.Users;
import com.gu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;

@Controller
@RequestMapping("user")

public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @GetMapping("{id}")
    @ResponseBody
    public Users queryUserById(@PathVariable("id") String id){

        return this.userService.queryUserById(id);
    }

}

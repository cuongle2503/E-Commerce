package com.example.Ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping("/admin/homepage")
    public String index(){
        return "admin/index";
    }

    @GetMapping("/admin/user")
    public String user(){
        return "admin/user";
    }
}

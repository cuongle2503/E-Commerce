package com.example.Ecommerce.controller.admin;

import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminUserController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/admin/user")
    public String user(HttpSession session, Model model){
        List<CustomerResponse> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "admin/user";
    }
}

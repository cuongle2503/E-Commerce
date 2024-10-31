package com.example.Ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    @GetMapping("/checkout")
    public String checkout(Model model,
                           HttpSession session){
        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);
        model.addAttribute("isLoggedIn", jwtToken != null);

        return "customer/home/checkout";
    }
}

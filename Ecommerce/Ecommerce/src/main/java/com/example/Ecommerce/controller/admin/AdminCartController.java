package com.example.Ecommerce.controller.admin;

import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminCartController {
    @Autowired
    CartService cartService;

    @GetMapping("/admin/cart")
    public String user(HttpSession session, Model model){
        List<CartResponse> carts = cartService.getCarts();
        model.addAttribute("carts", carts);

        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);

        if (jwtToken == null) {
            return "redirect:/admin/login";
        }
        return "admin/user";
    }
}

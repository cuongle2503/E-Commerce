package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.request.OrderRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.dto.response.OrderResponse;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
@Slf4j
@Controller
public class CheckoutController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;

    @GetMapping("/checkout")
    public String checkout(Model model,
                           HttpSession session) throws ParseException {
        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);

        model.addAttribute("isLoggedIn", jwtToken != null);

        if (jwtToken == null) {
            return "redirect:/login";
        }

        String cartId = (String) session.getAttribute("cartId");
        if (cartId == null) {
            return "redirect:/cart";
        }

        CartResponse cartResponse = cartService.getCartById(cartId);
        model.addAttribute("cartResponse", cartResponse);

        return "customer/home/checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(OrderRequest orderRequest, HttpSession session, Model model) {
        // Lấy cartId từ session
        String cartId = (String) session.getAttribute("cartId");
        if (cartId == null) {
            return "redirect:/cart"; // Nếu không có cartId, chuyển hướng về trang giỏ hàng
        }

        orderRequest.setCartId(cartId); // Thiết lập cartId vào OrderRequest

        // Lưu đơn hàng và nhận OrderResponse
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);

        // Thêm OrderResponse vào model để hiển thị trên trang
        model.addAttribute("orderResponse", orderResponse);

        return "redirect:/checkout";
    }
}

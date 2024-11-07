package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.service.CartService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String showHomePage(HttpSession session, Model model) throws ParseException {
        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);
        model.addAttribute("isLoggedIn", jwtToken != null);
        if (jwtToken == null) {
            return "redirect:/login";
        }

        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        String customerId = (String) signedJWT.getJWTClaimsSet().getClaim("customerId");

        CartResponse cartResponse = cartService.getCartByIdCustomer(customerId);
        model.addAttribute("carts", cartResponse);
        session.setAttribute("cartId", cartResponse.getId());

        return "customer/home/cart";
    }

    @PostMapping("/cart/remove") // Đường dẫn rõ ràng hơn
    public String deleteProductFromCart(@RequestParam String productId, HttpSession session) throws ParseException {
        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken == null) {
            return "redirect:/login";
        }

        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        String customerId = (String) signedJWT.getJWTClaimsSet().getClaim("customerId");

        cartService.deleteProductFromCart(customerId, productId);

        return "redirect:/cart";
    }
}

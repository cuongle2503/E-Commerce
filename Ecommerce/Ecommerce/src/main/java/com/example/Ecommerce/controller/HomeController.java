package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.List;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    private CartService cartService;

    //    -----------------HOME-----------------------------
    @GetMapping("/homepage")
    public String showHomePage(HttpSession session, Model model) {
        List<ProductResponse> products = productService.getProducts();
        model.addAttribute("products", products);

        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);

        model.addAttribute("isLoggedIn", jwtToken != null);

        return "customer/home/index";
    }
    //    --------------------------------------------------

    //    -----------------REGISTRATION---------------------
    @GetMapping("/register")
    public String signUpPage(Model model){
        model.addAttribute("customer", new CustomerRequest());
        return "customer/register/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid CustomerRequest request,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/register";
        }
        try {
            customerService.createAccount(request);
            redirectAttributes.addFlashAttribute("message",
                    "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }
    //    --------------------------------------------------

    //    -----------------LOGIN----------------------------
    @GetMapping("/login")
    public String signInPage(Model model, @ModelAttribute("message") String message){
        model.addAttribute("customer", new CustomerRequest());
        model.addAttribute("message", message);
        return "customer/login/login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid CustomerRequest request,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/login";
        }
        try {
            String token = customerService.signIn(request.getEmail(), request.getPassword());
            session.setAttribute("jwtToken", token);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
        return "redirect:/homepage";
    }
    //    --------------------------------------------------

    //    -----------------LOGOUT----------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "Bạn đã đăng xuất thành công.");
        return "redirect:/homepage";
    }
    //    --------------------------------------------------

    @PostMapping("/add")
    public String addToCart(@Valid CartRequest cartRequest,
                            HttpSession session) {

        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken == null) {
            return "redirect:/login";
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(jwtToken);
            String customerId = (String) signedJWT.getJWTClaimsSet().getClaim("customerId");

            if (customerId == null || customerId.isEmpty()) {
                return "redirect:/login";
            }

            // Đặt customer ID vào CartRequest trước khi gọi tầng service
            cartRequest.setCustomerId(customerId);

            // Gọi service để thêm giỏ hàng
            cartService.addCart(cartRequest);
            return "redirect:/homepage";
        } catch (ParseException e) {
            return "redirect:/login";
        }
    }
}

package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.ProductResponse;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    //    -----------------HOME-----------------------------
    @GetMapping("/homepage")
    public String showHomePage(Model model) {
        List<ProductResponse> products = productService.getProducts();
        model.addAttribute("products", products);
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
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/login";
        }
        try {
            String token = customerService.signIn(request.getEmail(), request.getPassword());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
        return "redirect:/homepage";
    }
    //    --------------------------------------------------

}

package com.example.Ecommerce.controller.admin;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.service.CustomerService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Slf4j
@Controller
public class AdminHomeController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/admin/homepage")
    public String index(HttpSession session, Model model){
        String jwtToken = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", jwtToken);

        return "admin/index";
    }

    //    -----------------LOGIN----------------------------
    @GetMapping("/admin/login")
    public String signInPage(Model model, @ModelAttribute("message") String message){
        model.addAttribute("customer", new CustomerRequest());
        model.addAttribute("message", message);


        return "admin/login";
    }

    @PostMapping("admin/login")
    public String processLogin(@Valid CustomerRequest request,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/login";
        }

        try {
            String token = customerService.signIn(request.getEmail(), request.getPassword());
            session.setAttribute("jwtToken", token);

            SignedJWT signedJWT = SignedJWT.parse(token);
            String scope = (String) signedJWT.getJWTClaimsSet().getClaim("scope");

            if (scope != null && scope.contains("ROLE_ADMIN")) {
                return "redirect:/admin/homepage";
            } else {
                return "redirect:/admin/login";
            }

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/login";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    // ---------------------------------------------------
}

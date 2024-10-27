package com.example.Ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS_API = {
            "/postman/customers/register",
            "/postman/customers/login",
            "/postman/customers/authenticate",
            "/postman/products/getProducts",
            "/postman/products/sortProductByPriceAsc",
            "/postman/products/sortProductByPriceDesc",
            "/postman/products/searchProductsByName/**",
            "/postman/products/filterProduct/**"
    };

    private final String[] PUBLIC_ENDPOINTS = {
            "/products",
            "/products/filterProduct",
            "/homepage",
            "/register",
            "/login"
    };

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSecurity httpSecurity1 = httpSecurity
                .authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_API)
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_API)
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS)
                                .permitAll()
                                .requestMatchers("customer/**")
                                .permitAll()
                                .anyRequest().authenticated()
                );
        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}

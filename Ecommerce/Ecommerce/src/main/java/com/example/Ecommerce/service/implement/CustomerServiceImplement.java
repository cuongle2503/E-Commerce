package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.request.IntrospectRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.IntrospectResponse;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.exception.AppException;
import com.example.Ecommerce.exception.ErrorCode;
import com.example.Ecommerce.mapper.CustomerMapper;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CustomerService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImplement implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRespository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public CustomerResponse createAccount(CustomerRequest request) {
        if(customerRespository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        return customerMapper.toCustomerResponse(customerRespository.save(customer));
    }

    @Override
    public String signIn(String email, String password) {
        if(!customerRespository.existsByEmail(email)){
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }

        Customer customer = customerRespository.findByEmail(email);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        var token = generateToken(email);

        return token;
    }

    @Override
    public String generateToken(String email) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new  JWTClaimsSet.Builder()
                .subject(email)
                .issuer("LeCuong")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customerClaim", "customer")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }

}
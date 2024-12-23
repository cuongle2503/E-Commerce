package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.request.IntrospectRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.dto.response.IntrospectResponse;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.exception.AppException;
import com.example.Ecommerce.exception.ErrorCode;
import com.example.Ecommerce.mapper.CustomerMapper;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.RoleRepository;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImplement implements CustomerService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRespository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

        Optional<Role> userRoleOpt = roleRepository.findById("USER");

        Role userRole = userRoleOpt.orElseGet(() -> {
            Role role = new Role();
            role.setName("USER");
            role.setDescription("User roles");
            return roleRepository.save(role);
        });

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        customer.setRoles(roles);

        return customerMapper.toCustomerResponse(customerRespository.save(customer));
    }

    @Override
//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    public List<CustomerResponse> getCustomers() {
        return customerRespository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
    }

    @Override
    public String signIn(String email, String password) {
        if(!customerRespository.existsByEmail(email)){
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }

        Customer customer = customerRespository.findByEmail(email);

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        var token = generateToken(customer);

        return token;
    }

    @Override
    public String generateToken(Customer customer) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new  JWTClaimsSet.Builder()
                .subject(customer.getEmail())
                .issuer("LeCuong")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customerId", customer.getId())
                .claim("scope", buildScope(customer))
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

    @PostAuthorize("returnObject.email == authentication.name")
    @Override
    public CustomerResponse getCustomer(String id) {
        return customerMapper.toCustomerResponse(customerRespository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.EMAIL_NOT_EXISTED)));
    }

    @Override
    public CustomerResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Customer customer = customerRespository.findByEmail(name);

        return customerMapper.toCustomerResponse(customer);
    }

    private String buildScope(Customer customer){
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(customer.getRoles()))
            customer.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));
            });

        return stringJoiner.toString();
    }
}
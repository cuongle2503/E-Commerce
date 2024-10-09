package com.example.Ecommerce.service.implement;


import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.exception.AppException;
import com.example.Ecommerce.exception.ErrorCode;
import com.example.Ecommerce.mapper.CustomerMapper;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRespository;

    @Override
    public CustomerResponse createAccount(CustomerRequest request) {
        if(customerRespository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        Customer customer = customerMapper.toCustomer(request);
        return customerMapper.toCustomerResponse(customerRespository.save(customer));
    }

    @Override
    public Boolean signIn(String email, String password) {
        if(!customerRespository.existsByEmail(email)){
            throw new AppException(ErrorCode.EMAIL_NOT_EXISTED);
        }
        Customer customer = customerRespository.findByEmail(email);
        if (!customer.getPassword().equals(password)) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }
        return true;
    }

}
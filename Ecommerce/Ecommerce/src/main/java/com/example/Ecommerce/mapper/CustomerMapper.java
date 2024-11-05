package com.example.Ecommerce.mapper;

import com.example.Ecommerce.dto.request.CustomerRequest;
import com.example.Ecommerce.dto.response.CustomerResponse;
import com.example.Ecommerce.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "roles", ignore = true)
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

}


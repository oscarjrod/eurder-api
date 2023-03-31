package com.oscarjrod.eurderapi.customers.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final ContactDetailsMapper contactDetailsMapper = new ContactDetailsMapper();

    // Customer => DTO
    public CustomerDto toDTO(Customer myCustomer) {
        return CustomerDto.createCustomerDto(myCustomer);
    }

    // DTO => Customer
    public Customer toCustomer(@Valid CustomerDto customerDto) {
        return Customer.createCustomer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                contactDetailsMapper.toContactDetails(customerDto.getContactDetails())
        );
    }

}
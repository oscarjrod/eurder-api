package com.oscarjrod.eurderapi.customers.service;

import com.oscarjrod.eurderapi.customers.domain.ContactDetailsMapper;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import com.oscarjrod.eurderapi.customers.domain.CustomerMapper;
import com.oscarjrod.eurderapi.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ContactDetailsMapper contactDetailsMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper, ContactDetailsMapper contactDetailsMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.contactDetailsMapper = contactDetailsMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer createdCustomer = customerMapper.toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(createdCustomer);

        return customerMapper.toDTO(savedCustomer);
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerRepository.findAll().forEach(
                customer -> customerDtos.add(CustomerDto.createCustomerDto(customer))
        );

        return customerDtos;
    }

}

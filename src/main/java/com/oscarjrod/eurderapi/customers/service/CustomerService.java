package com.oscarjrod.eurderapi.customers.service;

import com.oscarjrod.eurderapi.customers.domain.ContactDetailsMapper;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import com.oscarjrod.eurderapi.customers.domain.CustomerMapper;
import com.oscarjrod.eurderapi.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private ContactDetailsMapper contactDetailsMapper;

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

}

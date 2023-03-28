package com.oscarjrod.eurderapi.customers.api;

import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import com.oscarjrod.eurderapi.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

}

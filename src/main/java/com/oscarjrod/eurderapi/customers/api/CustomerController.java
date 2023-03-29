package com.oscarjrod.eurderapi.customers.api;

import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import com.oscarjrod.eurderapi.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public Long createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Long> getAllCustomersNoDetails() {
        return customerService.getAllCustomers().stream()
                .map(CustomerDto::getId)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", path = "/details")
    public List<CustomerDto> getAllCustomersWithDetails() {
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public Long getCustomerByIdNoDetails(@PathVariable Long id) {
        return customerService.getCustomerById(id).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}/details")
    public CustomerDto getCustomerByIdWithDetails(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

}
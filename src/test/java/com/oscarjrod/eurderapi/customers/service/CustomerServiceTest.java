package com.oscarjrod.eurderapi.customers.service;

import com.oscarjrod.eurderapi.customers.domain.*;
import com.oscarjrod.eurderapi.customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ContactDetails contactDetails = ContactDetails.createContactDetails(
                "test@example.com", "123 Test Street", "555-1234");
        customer = Customer.createCustomer("John", "Doe", contactDetails);

        customerDto = CustomerDto.createCustomerDto(customer);
    }

    @Test
    void testCreateCustomer() {
        when(customerMapper.toCustomer(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.createCustomer(customerDto);

        assertEquals(customerDto, result);
        verify(customerMapper).toCustomer(customerDto);
        verify(customerRepository).save(customer);
        verify(customerMapper).toDTO(customer);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        CustomerDto customerDto1 = CustomerDto.createCustomerDto(customers.get(0));
        CustomerDto customerDto2 = CustomerDto.createCustomerDto(customers.get(1));
        List<CustomerDto> expected = Arrays.asList(customerDto1, customerDto2);

        List<CustomerDto> result = customerService.getAllCustomers();

        assertEquals(expected, result);

        verify(customerRepository, times(1)).findAll();
    }


    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById(1L);

        assertEquals(customerDto, result);
        verify(customerRepository).findById(1L);
        verify(customerMapper).toDTO(customer);
    }

}

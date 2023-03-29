package com.oscarjrod.eurderapi.customers.api;

import com.oscarjrod.eurderapi.customers.domain.ContactDetails;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import com.oscarjrod.eurderapi.customers.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private final ContactDetails contactDetails = ContactDetails.createContactDetails(
            "johndoe@example.com", "123 Main St, Anytown CA 12345", "+1 (555) 123-4567");
    private Customer customer;
    private CustomerDto customerDto;
    private CustomerDto customerDto2;
    private CustomerService customerService;
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);
        customer = Customer.createCustomer("John", "Doe", contactDetails);
        customerDto = CustomerDto.createCustomerDto(customer);
        customerDto2 = CustomerDto.createCustomerDto(customer);
    }

    @Test
    void testCreateCustomer() {
        when(customerService.createCustomer(customerDto)).thenReturn(CustomerDto.createCustomerDto(customer));

        Long result = customerController.createCustomer(customerDto);

        Assertions.assertEquals(customer.getId(), result);
        verify(customerService, times(1)).createCustomer(customerDto);
    }

    @Test
    void testGetAllCustomersNoDetails() {
        Long id = 1L;
        Long id2 = 2L;

        List<CustomerDto> customers = new ArrayList<>();
        customerDto.setId(id);
        customers.add(customerDto);
        customerDto2.setId(id2);
        customers.add(customerDto2);

        List<Long> expected = new ArrayList<>();
        expected.add(id);
        expected.add(id2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        List<Long> result = customerController.getAllCustomersNoDetails();

        Assertions.assertEquals(expected, result);
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testGetAllCustomersWithDetails() {
        List<CustomerDto> expected = new ArrayList<>();
        expected.add(new CustomerDto());
        expected.add(new CustomerDto());

        when(customerService.getAllCustomers()).thenReturn(expected);

        List<CustomerDto> result = customerController.getAllCustomersWithDetails();

        Assertions.assertEquals(expected, result);
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerByIdNoDetails() {
        Long id = 1L;
        customerDto.setId(id);
        when(customerService.getCustomerById(id)).thenReturn(customerDto);

        Long result = customerController.getCustomerByIdNoDetails(id);

        Assertions.assertEquals(id, result);
        verify(customerService, times(1)).getCustomerById(id);
    }

    @Test
    void testGetCustomerByIdWithDetails() {
        Long id = 1L;

        when(customerService.getCustomerById(id)).thenReturn(customerDto);

        CustomerDto result = customerController.getCustomerByIdWithDetails(id);

        Assertions.assertEquals(customerDto, result);
        verify(customerService, times(1)).getCustomerById(id);
    }

}

package com.oscarjrod.eurderapi.customers.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerMapperTest {

    private Customer customer;
    private CustomerDto customerDto;

    @Autowired
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        ContactDetails contactDetails = ContactDetails.createContactDetails(
                "test@example.com", "123 Test Street", "555-1234");
        customer = Customer.createCustomer("John", "Doe", contactDetails);
        customerDto = customerMapper.toDTO(customer);
    }

    @Test
    void testCustomerToCustomerDto() {
        CustomerDto convertedCustomerDto = customerMapper.toDTO(customer);

        assertEquals(customerDto.getId(), convertedCustomerDto.getId());
        assertEquals(customerDto.getFirstName(), convertedCustomerDto.getFirstName());
        assertEquals(customerDto.getLastName(), convertedCustomerDto.getLastName());
        assertEquals(customerDto.getContactDetails().getId(), convertedCustomerDto.getContactDetails().getId());
        assertEquals(customerDto.getContactDetails().getEmailAddress(),
                convertedCustomerDto.getContactDetails().getEmailAddress());
        assertEquals(customerDto.getContactDetails().getAddress(),
                convertedCustomerDto.getContactDetails().getAddress());
        assertEquals(customerDto.getContactDetails().getPhoneNumber(),
                convertedCustomerDto.getContactDetails().getPhoneNumber());
    }

    @Test
    void testCustomerDtoToCustomer() {
        Customer convertedCustomer = customerMapper.toCustomer(customerDto);

        assertEquals(customer.getId(), convertedCustomer.getId());
        assertEquals(customer.getFirstName(), convertedCustomer.getFirstName());
        assertEquals(customer.getLastName(), convertedCustomer.getLastName());
        assertEquals(customer.getContactDetails().getId(), convertedCustomer.getContactDetails().getId());
        assertEquals(customer.getContactDetails().getEmailAddress(),
                convertedCustomer.getContactDetails().getEmailAddress());
        assertEquals(customer.getContactDetails().getAddress(),
                convertedCustomer.getContactDetails().getAddress());
        assertEquals(customer.getContactDetails().getPhoneNumber(),
                convertedCustomer.getContactDetails().getPhoneNumber());
    }

}
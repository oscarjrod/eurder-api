package com.oscarjrod.eurderapi.customers;

import com.oscarjrod.eurderapi.customers.domain.ContactDetails;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.customers.domain.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerIntegrationTest {

    private final ContactDetails contactDetails = ContactDetails.createContactDetails(
            "johndoe@example.com", "123 Main St, Anytown CA 12345", "0475963214");

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    void givenAnCustomer_whenAddingCustomerToController_thenRepositoryContainsThatCustomer() {
        //GIVEN
        CustomerDto customerDto = CustomerDto.createCustomerDto(
                Customer.createCustomer("John", "Doe", contactDetails)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("developer", "password");

        HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto, headers);

        //WHEN
        ResponseEntity<Void> response = restTemplate.exchange(
                "/customers",
                HttpMethod.POST,
                request,
                Void.class
        );

        //Then
        ResponseEntity<CustomerDto> customerDetailsResponse = restTemplate.exchange(
                "/customers/1/details",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CustomerDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, customerDetailsResponse.getStatusCode());
        assertEquals(1, Objects.requireNonNull(customerDetailsResponse.getBody()).getId());
        assertEquals("John", customerDetailsResponse.getBody().getFirstName());
        assertEquals("Doe", customerDetailsResponse.getBody().getLastName());
        assertEquals(1, customerDetailsResponse.getBody().getContactDetails().getId());
        assertEquals("johndoe@example.com",
                customerDetailsResponse.getBody().getContactDetails().getEmailAddress());
        assertEquals("123 Main St, Anytown CA 12345",
                customerDetailsResponse.getBody().getContactDetails().getAddress());
        assertEquals("0475963214",
                customerDetailsResponse.getBody().getContactDetails().getPhoneNumber());
    }

}

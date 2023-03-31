package com.oscarjrod.eurderapi.customers.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ContactDetailsMapperTest {

    private ContactDetails contactDetails;
    private ContactDetailsDto contactDetailsDto;
    @Autowired
    private ContactDetailsMapper contactDetailsMapper;

    @BeforeEach
    void setUp() {
        contactDetails = ContactDetails.createContactDetails(
                "test@example.com", "123 Test Street", "0475963214");
        contactDetailsDto = contactDetailsMapper.toDTO(contactDetails);
    }

    @Test
    void testContactDetailsToContactDetailsDto() {
        ContactDetailsDto convertedContactDetailsDto = contactDetailsMapper.toDTO(contactDetails);

        assertEquals(contactDetailsDto.getId(), convertedContactDetailsDto.getId());
        assertEquals(contactDetailsDto.getEmailAddress(), convertedContactDetailsDto.getEmailAddress());
        assertEquals(contactDetailsDto.getAddress(), convertedContactDetailsDto.getAddress());
        assertEquals(contactDetailsDto.getPhoneNumber(), convertedContactDetailsDto.getPhoneNumber());
    }

    @Test
    void testContactDetailsDtoToContactDetails() {
        ContactDetails convertedContactDetails = contactDetailsMapper.toContactDetails(contactDetailsDto);

        assertEquals(contactDetails.getId(), convertedContactDetails.getId());
        assertEquals(contactDetails.getEmailAddress(), convertedContactDetails.getEmailAddress());
        assertEquals(contactDetails.getAddress(), convertedContactDetails.getAddress());
        assertEquals(contactDetails.getPhoneNumber(), convertedContactDetails.getPhoneNumber());
    }

}
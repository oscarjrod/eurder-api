package com.oscarjrod.eurderapi.customers.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailsMapper {

    // ContactDetails => DTO
    public ContactDetailsDto toDTO(ContactDetails contactDetails) {
        return ContactDetailsDto.createContactDetailsDto(contactDetails);
    }

    // DTO => ContactDetails
    public ContactDetails toContactDetails(@Valid ContactDetailsDto contactDetailsDto) {
        return ContactDetails.createContactDetails(
                contactDetailsDto.getEmailAddress(),
                contactDetailsDto.getAddress(),
                contactDetailsDto.getPhoneNumber()
        );
    }

}

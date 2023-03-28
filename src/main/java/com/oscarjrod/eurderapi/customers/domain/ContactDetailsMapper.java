package com.oscarjrod.eurderapi.customers.domain;

import org.springframework.stereotype.Component;

@Component
public class ContactDetailsMapper {

    // ContactDetails => DTO
    public ContactDetailsDto toDTO(ContactDetails contactDetails) {
        return ContactDetailsDto.fromContactDetails(contactDetails);
    }

    // DTO => ContactDetails
    public ContactDetails toContactDetails(ContactDetailsDto contactDetailsDto) {
        return ContactDetails.createContactDetails(
                contactDetailsDto.getEmailAddress(),
                contactDetailsDto.getAddress(),
                contactDetailsDto.getPhoneNumber()
        );
    }

}

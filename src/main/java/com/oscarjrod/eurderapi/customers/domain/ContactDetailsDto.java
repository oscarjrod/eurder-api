package com.oscarjrod.eurderapi.customers.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactDetailsDto {

    private Long id;
    @NotBlank(message = "Email address cannot be empty!")
    @Email(message = "Invalid email address format!")
    private String emailAddress;
    @NotBlank(message = "Address cannot be empty!")
    @Size(max = 100, message = "Address cannot be longer than 100 characters!")
    private String address;
    @NotBlank(message = "Phone number cannot be empty!")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number!")
    private String phoneNumber;

    public static ContactDetailsDto createContactDetailsDto(ContactDetails contactDetails) {
        ContactDetailsDto dto = new ContactDetailsDto();
        dto.setId(contactDetails.getId());
        dto.setEmailAddress(contactDetails.getEmailAddress());
        dto.setAddress(contactDetails.getAddress());
        dto.setPhoneNumber(contactDetails.getPhoneNumber());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "emailAddress = " + emailAddress +
                ", address = " + address +
                ", phoneNumber = " + phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactDetailsDto that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getEmailAddress() != null ? !getEmailAddress().equals(that.getEmailAddress()) : that.getEmailAddress() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmailAddress() != null ? getEmailAddress().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

}


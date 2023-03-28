package com.oscarjrod.eurderapi.customers.domain;

import jakarta.persistence.*;

@Entity
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emailAddress;
    private String address;
    private String phoneNumber;
    @OneToOne(mappedBy = "contactDetails", cascade = CascadeType.ALL)
    private Customer customer;

    public static ContactDetails createContactDetails(String emailAddress, String address, String phoneNumber) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmailAddress(emailAddress);
        contactDetails.setAddress(address);
        contactDetails.setPhoneNumber(phoneNumber);
        return contactDetails;
    }

    public Long getId() {
        return id;
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
        if (!(o instanceof ContactDetails that)) return false;

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

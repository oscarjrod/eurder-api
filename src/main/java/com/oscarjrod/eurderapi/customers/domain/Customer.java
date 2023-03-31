package com.oscarjrod.eurderapi.customers.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First name cannot be empty!")
    @Size(max = 20, message = "First name cannot be longer than 20 characters!")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty!")
    @Size(max = 20, message = "Last name cannot be longer than 20 characters!")
    private String lastName;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id", referencedColumnName = "id")
    private ContactDetails contactDetails;

    public static Customer createCustomer(String firstName, String lastName, ContactDetails contactDetails) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContactDetails(contactDetails);
        return customer;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "Customer {" +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", contactDetails = " + contactDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;

        if (getId() != null ? !getId().equals(customer.getId()) : customer.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(customer.getFirstName()) : customer.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(customer.getLastName()) : customer.getLastName() != null)
            return false;
        return getContactDetails() != null ? getContactDetails().equals(customer.getContactDetails()) : customer.getContactDetails() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getContactDetails() != null ? getContactDetails().hashCode() : 0);
        return result;
    }

}

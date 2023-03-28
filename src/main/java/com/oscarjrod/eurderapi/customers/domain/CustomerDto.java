package com.oscarjrod.eurderapi.customers.domain;

public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private ContactDetailsDto contactDetails;

    public static CustomerDto fromCustomer(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setContactDetails(ContactDetailsDto.fromContactDetails(customer.getContactDetails()));
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ContactDetailsDto getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsDto contactDetails) {
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
        if (!(o instanceof CustomerDto that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        return getContactDetails() != null ? getContactDetails().equals(that.getContactDetails()) : that.getContactDetails() == null;
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


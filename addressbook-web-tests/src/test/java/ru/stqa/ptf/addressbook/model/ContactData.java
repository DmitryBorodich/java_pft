package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String mobile;
    private final String email;
    private final String address;

    public ContactData(String firstname, String lastname, String mobile, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}

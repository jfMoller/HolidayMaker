package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String personalNumber;
    private String email;
    private String passportNumber;

    public Customer(String firstName, String lastName, String phoneNumber, String personalNumber, String email, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.personalNumber = personalNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public Customer(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.firstName = resultSet.getString("first_name");
        this.lastName = resultSet.getString("last_name");
        this.phoneNumber = resultSet.getString("phone_number");
        this.personalNumber = resultSet.getString("personal_number");
        this.email = resultSet.getString("email");
        this.passportNumber = resultSet.getString("passport_number");
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}

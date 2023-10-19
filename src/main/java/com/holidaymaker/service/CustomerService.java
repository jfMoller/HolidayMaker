package com.holidaymaker.service;

import com.holidaymaker.entity.Customer;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {

    private final Connection connection;

    private final Scanner scanner;

    public CustomerService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public void viewCustomers() throws SQLException {
        List<Customer> customers = getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public List<Customer> getCustomers() throws SQLException {

        String sql = "SELECT * FROM customers";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            customers.add(new Customer(resultSet));
        }

        return customers;
    }

    // Adds a customer to the database through the menu
    public void addMenuCustomer() throws SQLException {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter personal number:");
        String personalNumber = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter passport number:");
        String passportNumber = scanner.nextLine();

        Customer newCustomer = new Customer(
                firstName, lastName, phoneNumber, personalNumber, email, passportNumber);
        addCustomer(newCustomer);
    }

    // Adds a customer to the database
    public void addCustomer(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO customers (first_name, last_name, phone_number, personal_number, email, passport_number)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, newCustomer.getFirstName());
        statement.setString(2, newCustomer.getLastName());
        statement.setString(3, newCustomer.getPhoneNumber());
        statement.setString(4, newCustomer.getPersonalNumber());
        statement.setString(5, newCustomer.getEmail());
        statement.setString(6, newCustomer.getPassportNumber());

        statement.executeUpdate();
    }

}

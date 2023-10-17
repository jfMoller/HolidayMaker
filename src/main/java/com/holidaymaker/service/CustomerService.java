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
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        Customer newCustomer = new Customer(name);
        addCustomer(newCustomer);
    }

    // Adds a customer to the database
    public void addCustomer(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO customers (name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newCustomer.getName());

        statement.executeUpdate();
    }

}

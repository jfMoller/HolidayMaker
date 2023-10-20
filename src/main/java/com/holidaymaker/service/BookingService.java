package com.holidaymaker.service;

import com.holidaymaker.entity.Booking;
import com.holidaymaker.entity.Customer;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingService {

    private final Connection connection;

    private final Scanner scanner;

    public BookingService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public Booking fetchLastBooking() throws SQLException {

        String sql = "SELECT * FROM bookings ORDER BY id DESC LIMIT 1;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();


       if (resultSet.next()) {
            return new Booking(resultSet);
        }
        return null;
    }

    public Booking findBooking(Booking booking) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE   (main_customer, date, isPayed, travel_package)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, booking.getMain_customer());
        statement.setString(2, booking.getDate());
        statement.setBoolean(3, booking.getIsPayed());
        statement.setInt(4, booking.getTravel_package());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Booking(resultSet);
        }
        return null;
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


}

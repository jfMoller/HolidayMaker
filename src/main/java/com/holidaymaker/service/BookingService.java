package com.holidaymaker.service;

import com.holidaymaker.entity.Booking;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingService {

    private final Connection connection;

    private final Scanner scanner;

    public BookingService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public void addBooking(Booking newBooking) throws SQLException {
        String sql = "INSERT INTO bookings (main_customer, date, isPayed, travel_package)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement statement = setBookingStatement(newBooking, sql);
        statement.executeUpdate();
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
        String sql = "SELECT * FROM bookings " +
                "WHERE main_customer = (?) AND date = (?) AND isPayed =(?) AND travel_package = (?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, booking.getMainCustomer());
        statement.setString(2, booking.getDate());
        statement.setBoolean(3, booking.getIsPayed());
        statement.setInt(4, booking.getTravelPackage());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Booking(resultSet);
        }
        return null;
    }

    public void deleteBooking(Booking booking) throws SQLException {
        if (findBooking(booking) != null) {

            String sql = "DELETE FROM bookings " +
                    "WHERE main_customer = (?) AND date = (?) AND isPayed =(?) AND travel_package = (?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, booking.getMainCustomer());
            statement.setString(2, booking.getDate());
            statement.setBoolean(3, booking.getIsPayed());
            statement.setInt(4, booking.getTravelPackage());

            statement.executeUpdate();
        }
    }

    public PreparedStatement setBookingStatement(Booking newBooking, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, newBooking.getMainCustomer());
        statement.setString(2, newBooking.getDate());
        statement.setBoolean(3, newBooking.getIsPayed());
        statement.setInt(4, newBooking.getTravelPackage());

        return statement;
    }


    // Adds a customer to the database


}

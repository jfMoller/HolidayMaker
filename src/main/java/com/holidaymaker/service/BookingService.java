package com.holidaymaker.service;

import com.holidaymaker.entity.Booking;
import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookingService {

    private final Connection connection;

    private final Scanner scanner;

    public BookingService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public void showVerifyPayment() throws SQLException {
        System.out.println("Enter booking Id to verify payment: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        verifyPayment(bookingId);
        if (getPayStatus(bookingId)) {
            System.out.println("Payment confirmed for booking with ID: " + bookingId);
        } else {
            System.out.println("Failed to confirm payment.");
        }

    }

    public void viewMakeBooking() throws SQLException {
        System.out.println("Please enter customer id: ");
        int customer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the travel package number");
        int travelPackage = scanner.nextInt();
        scanner.nextLine();

        boolean isSuccessful = makeBooking(customer, travelPackage);

        if(isSuccessful) {
            System.out.println("You have successfully added a new booking to our system.");
        } else {
            System.out.println("Booking has not been added to our system. Please try again.");
        }

    }

    public boolean makeBooking(int customer, int travelPackage) throws SQLException {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Booking booking = new Booking(customer, formattedDateTime, false, travelPackage);
        addBooking(booking);
        if (findBooking(booking) != null) {
            return true;
        }
        return false;
    }

    public void addBooking(Booking newBooking) throws SQLException {
        String sql = "INSERT INTO bookings (main_customer, date, isPayed, travel_package)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement statement = setBookingStatement(newBooking, sql);
        statement.executeUpdate();
    }

    public List<Booking> getAllBookings() throws SQLException {

        String sql = "SELECT * FROM bookings";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Booking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            bookings.add(new Booking(resultSet));
        }

        return bookings;
    }

    public void viewBookings() throws SQLException {
        List<Booking> bookings = getAllBookings();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
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

        PreparedStatement statement = setBookingStatement(booking, sql);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Booking(resultSet);
        }
        return null;
    }

    public Booking findBooking(int bookingId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE id = (?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, bookingId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Booking(resultSet);
        }
        return null;
    }

    public int findBookingId(Booking booking) throws SQLException {
        Booking foundBooking = findBooking(booking);
        return foundBooking.getId();
    };

    public void verifyPayment(int bookingId) throws SQLException {
        String sql = "UPDATE bookings SET isPayed = true WHERE id = (?) ";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, bookingId);
        statement.executeUpdate();
    }

    public Boolean getPayStatus(int bookingId) throws SQLException {
        return findBooking(bookingId).getIsPayed();
    };

    public void deleteBooking(Booking booking) throws SQLException {
        if (findBooking(booking) != null) {
            String sql = "DELETE FROM bookings " +
                    "WHERE main_customer = (?) AND date = (?) AND isPayed =(?) AND travel_package = (?)";

            PreparedStatement statement = setBookingStatement(booking, sql);

            statement.executeUpdate();
        }
    }

    public void deleteBooking(int bookingId) throws SQLException {
        if (findBooking(bookingId) != null) {
            String sql = "DELETE FROM bookings " +
                    "WHERE id = (?) ";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookingId);

            statement.executeUpdate();
        }
    }

    public void showDeleteBooking() throws SQLException{
        System.out.println("Enter booking Id for the booking you want to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        deleteBooking(bookingId);
        if (findBooking(bookingId) == null) {
            System.out.println("Booking with ID: " + bookingId + " has been canceled!");
        }  else {
            System.out.println("Failed to cancel booking.");
        }

    };



    public String printCustomer(int mainCustomer) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = (?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, mainCustomer);

        ResultSet resultSet = statement.executeQuery();

        StringBuilder stringBuilder = new StringBuilder();

        if (resultSet.next()) {
            String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
            String personalNumber = resultSet.getString("personal_number");
            String email = resultSet.getString("email");
            stringBuilder.append("\n\t - " + name + ", personal number: " + personalNumber + ", email: " + email);
        }
        return stringBuilder.toString();
    }

    public String printTravelPackage(int travelPackage) throws SQLException {
        String sql = "SELECT * FROM travel_packages WHERE id = (?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, travelPackage);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new TravelPackage(resultSet).toString();
        }
        return null;
    }

    public PreparedStatement setBookingStatement(Booking booking, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, booking.getMainCustomer());
        statement.setString(2, booking.getDate());
        statement.setBoolean(3, booking.getIsPayed());
        statement.setInt(4, booking.getTravelPackage());

        return statement;
    }

}

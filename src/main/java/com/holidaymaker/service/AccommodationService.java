package com.holidaymaker.service;
import com.holidaymaker.entity.Accommodation;
import com.holidaymaker.entity.Booking;
import com.holidaymaker.utility.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccommodationService {

    private final Connection connection;

    private final Scanner scanner;

    public AccommodationService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }
    public List<Accommodation> getAllAccommodations() throws SQLException {

        String sql = "SELECT * FROM accommodations";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Accommodation> accommodationTypes = new ArrayList<>();

        while (resultSet.next()) {
            accommodationTypes.add(new Accommodation(resultSet));
        }

        return accommodationTypes;
    }

    public void createAccommodation(Accommodation newAccommodation) throws SQLException{
        String sql = "INSERT INTO accommodations (type, price, number_of_beds, travel_package) " +
                "VALUES (?, ?, ?, ?) ";
        PreparedStatement statement = setAccommodationStatement(newAccommodation, sql);

        statement.executeUpdate();
    }

    public Accommodation findAccommodation(Accommodation accommodation) throws SQLException {
        String sql = "SELECT * FROM accommodations " +
                "WHERE type = (?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, accommodation.getType());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Accommodation(resultSet);
        }
        return null;
    }

    public PreparedStatement setAccommodationStatement(Accommodation accommodation, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, accommodation.getType());
        statement.setDouble(2, accommodation.getPrice());
        statement.setInt(3, accommodation.getNumberOfBeds());
        statement.setInt(4, accommodation.getTravelPackage());

        return statement;
    }






}

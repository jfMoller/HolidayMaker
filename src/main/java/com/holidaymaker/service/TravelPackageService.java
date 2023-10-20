package com.holidaymaker.service;

import com.holidaymaker.entity.Activity;
import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelPackageService {
    private final Connection connection;

    private final Scanner scanner;

    public TravelPackageService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public void viewTravelPackage() throws SQLException {
        List<TravelPackage> travelPackages = getTravelPackage();
        for (TravelPackage travelPackage : travelPackages) {
            System.out.println(travelPackage);
        }
    }

    public List<TravelPackage> getTravelPackage() throws SQLException {
        List<TravelPackage> travelPackageList = new ArrayList<>();

        String sql = "SELECT * FROM travel_packages";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Activity> activities = new ArrayList<>();

        while (resultSet.next()) {
            travelPackageList.add(new TravelPackage(resultSet));
        }

        return travelPackageList;
    }

    public void addTravelPackage(TravelPackage travelPackage) throws SQLException {
        String sql = "INSERT INTO travel_packages (total_price, theme, destination, available_spots, start_date, end_date)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, travelPackage.getPrice());
        statement.setInt(2, travelPackage.getTheme());
        statement.setString(3, travelPackage.getDestination());
        statement.setInt(4, travelPackage.getAvailableSpots());
        statement.setString(5, travelPackage.getStartDate());
        statement.setString(6, travelPackage.getEndDate());

        statement.executeUpdate();

    }

    //Påbörjad metod för att kunna skräddarsy resor men används inte just nu.
/*

    public void addTravelPackageFromMenu() throws SQLException {
        System.out.println("Type of theme 1: Sport 2: Culture 3: Nature");
        int theme = scanner.nextInt();

        System.out.println("Destination");
    }


 */
}

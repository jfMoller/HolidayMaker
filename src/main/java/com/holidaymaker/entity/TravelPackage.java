package com.holidaymaker.entity;

import com.holidaymaker.service.TravelPackageService;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelPackage {

    private int id;
    private int price;
    private int theme;
    private String destination;
    private int availableSpots;
    private String startDate;
    private String endDate;
    private Connection connection;

    private TravelPackageService travelPackageService = new TravelPackageService();


    public TravelPackage(int price, int theme, String destination, int availableSpots, String startDate, String endDate) {
        this.price = price;
        this.theme = theme;
        this.destination = destination;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TravelPackage(ResultSet resultSet) throws SQLException {
        this.connection = ConnectionProvider.getConnection();
        this.id = resultSet.getInt("id");
        this.price = resultSet.getInt("total_price");
        this.theme = resultSet.getInt("theme");
        this.destination = resultSet.getString("destination");
        this.availableSpots = resultSet.getInt("available_spots");
        this.startDate = resultSet.getString("start_date");
        this.endDate = resultSet.getString("end_date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        try {
            return String.format("-".repeat(30) +
                            " Travel-package %s " + "-".repeat(30) + "\n" +
                            "Theme: %s" + "\n" +
                            "Destination: %s " + "\n" +
                            "Activities: %s " + "\n" +
                            "Accommodations: %s " + "\n" +
                            "Additional services: %s " + "\n" +
                            "Dates: %s - %s " + "\n" +
                            "Available spots: %s " + "\n" +
                            "Total price: %.2f" + "\n"
                    ,
                    id, travelPackageService.printThemeAsString(theme), destination,
                    travelPackageService.formatActivities(id),
                    travelPackageService.formatAccommodations(id),
                    travelPackageService.formatAdditionalServices(id),
                    startDate, endDate, availableSpots, price + travelPackageService.calculateTotalAdditionalPrice(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

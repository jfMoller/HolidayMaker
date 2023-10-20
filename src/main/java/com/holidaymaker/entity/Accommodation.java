package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Accommodation {

    private int id;
    private static final List<String> types = new ArrayList<>(List.of("Hotel", "Hostel", "Cottage"));
    private double price;
    private int numberOfBeds;
    private int travelPackage;
    private String type;

    public Accommodation( double price, int numberOfBeds) {
        this.price = price;
        this.numberOfBeds = numberOfBeds;
    }

    public Accommodation(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.type = resultSet.getString("type");
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<String> getAllTypes() { return types; }

    public double getPrice() { return price; }

    public int getNumberOfBeds() { return numberOfBeds; }

    public int getTravelPackage() { return travelPackage; }
}

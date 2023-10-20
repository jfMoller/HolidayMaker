package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdditionalService {

    private int id;

    private double price;

    private String description;

    private int travelPackageId;

    public AdditionalService(double price, String description) {
        this.price = price;
        this.description = description;
    }

    public AdditionalService(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.price = resultSet.getDouble("price");
        this.description = resultSet.getString("description");
        this.travelPackageId = resultSet.getInt("travel_package");
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getTravelPackage() {
        return travelPackageId;
    }

    @Override
    public String toString() {
        return "AdditionalService{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", travelPackageId=" + travelPackageId +
                '}';
    }
}

package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TravelPackage {

    private int id;
    private int price;
    private int theme;
    private  String destination;
    private int availableSpots;
    private String startDate;
    private String endDate;

    public TravelPackage(int price, int theme, String destination, int availableSpots, String startDate, String endDate) {
        this.price = price;
        this.theme = theme;
        this.destination = destination;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TravelPackage(ResultSet resultSet) throws SQLException {
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
        return "TravelPackage{" +
                "id=" + id +
                ", price=" + price +
                ", theme=" + theme +
                ", destination='" + destination + '\'' +
                ", availableSpots=" + availableSpots +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

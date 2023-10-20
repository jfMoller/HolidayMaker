package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TravelPackage {

    private int id;
    private double price;
    private int theme;
    private  String destination;
    private int availableSpots;
    private Date startDate;
    private Date endDate;

    public TravelPackage(int id, double price, int theme, String destination, int availableSpots, Date startDate, Date endDate) {
        this.id = id;
        this.price = price;
        this.theme = theme;
        this.destination = destination;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TravelPackage(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.price = resultSet.getInt("price");
        this.theme = resultSet.getInt("theme");
        this.destination = resultSet.getString("destination");
        this.availableSpots = resultSet.getInt("available_spots");
        this.startDate = resultSet.getDate("start_date");
        this.endDate = resultSet.getDate("end_date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

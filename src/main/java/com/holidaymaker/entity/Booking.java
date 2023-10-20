package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {

    private int id;
    private int mainCustomer;
    private String date;
    private boolean isPayed;
    private int travelPackage;

    public Booking(int mainCustomer, String date, boolean isPayed, int travelPackage) {
        this.mainCustomer = mainCustomer;
        this.date = date;
        this.isPayed = isPayed;
        this.travelPackage = travelPackage;
    }

    public Booking(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.mainCustomer = resultSet.getInt("main_customer");
        this.date = resultSet.getString("date");
        this.isPayed = resultSet.getBoolean("isPayed");
        this.travelPackage = resultSet.getInt("travel_package");
    }

    public int getId() {
        return id;
    }

    public int getMainCustomer() {
        return mainCustomer;
    }

    public String getDate() {
        return date;
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public int getTravelPackage() {
        return travelPackage;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", mainCustomer=" + mainCustomer +
                ", date='" + date + '\'' +
                ", isPayed=" + isPayed +
                ", travelPackage=" + travelPackage +
                '}';
    }
}

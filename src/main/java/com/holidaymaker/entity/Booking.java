package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {

    private int id;
    private int main_customer;
    private String date;
    private boolean isPayed;
    private int travel_package;

    public Booking(int main_customer, String date, boolean isPayed, int travel_package) {
        this.main_customer = main_customer;
        this.date = date;
        this.isPayed = isPayed;
        this.travel_package = travel_package;
    }

    public Booking(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.main_customer = resultSet.getInt("main_customer");
        this.date = resultSet.getString("date");
        this.isPayed = resultSet.getBoolean("isPayed");
        this.travel_package = resultSet.getInt("travel_package");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMain_customer() {
        return main_customer;
    }

    public void setMain_customer(int main_customer) {
        this.main_customer = main_customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean payed) {
        isPayed = payed;
    }

    public int getTravel_package() {
        return travel_package;
    }

    public void setTravel_package(int travel_package) {
        this.travel_package = travel_package;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", main_customer=" + main_customer +
                ", date='" + date + '\'' +
                ", isPayed=" + isPayed +
                ", travel_package=" + travel_package +
                '}';
    }
}

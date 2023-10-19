package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private int id;

    private String type;

    private int price;

    public Activity() {
    }

    public Activity(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.type = resultSet.getString("type");
        this.price = resultSet.getInt("price");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}

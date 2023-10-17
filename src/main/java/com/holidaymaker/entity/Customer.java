package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private int id;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

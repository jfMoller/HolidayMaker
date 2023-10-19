package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Theme {

    private int id;
    private static final List<String> types = new ArrayList<>(List.of("Sport", "Culture", "Nature"));

    private String type;

    public Theme() {
    }

    public Theme(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.type = resultSet.getString("type");
    }

    public List<String> getAllTypes() {
        return types;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

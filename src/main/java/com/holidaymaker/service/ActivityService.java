package com.holidaymaker.service;

import com.holidaymaker.entity.Activity;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActivityService {
    private final Connection connection;

    private final Scanner scanner;

    public ActivityService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public List<Activity> getActivities() throws SQLException {

        String sql = "SELECT * FROM activies";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Activity> activities = new ArrayList<>();

        while (resultSet.next()) {
            activities.add(new Activity(resultSet));
        }

        return activities;
    }

}

package com.holidaymaker.service;

import com.holidaymaker.entity.Customer;
import com.holidaymaker.entity.Theme;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThemeService {

    private final Connection connection;

    private final Scanner scanner;

    public ThemeService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public List<Theme> getAllThemes() throws SQLException {

        String sql = "SELECT * FROM themes";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Theme> themeTypes = new ArrayList<>();

        while (resultSet.next()) {
            themeTypes.add(new Theme(resultSet));
        }

        return themeTypes;
    }

}

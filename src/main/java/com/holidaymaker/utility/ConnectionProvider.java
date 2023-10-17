package com.holidaymaker.utility;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionProvider {

    private static final String connectionString = getConnectionString();

    private static Connection connection;

    public static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(connectionString);

        String sql = "USE HolidayDB";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    private static String getConnectionString() {
        InputStream inputStream = ConnectionProvider.class.getClassLoader().getResourceAsStream("credentials.yml");
        Map<String, String> config = new Yaml().load(inputStream);

        return config.get("connectionString");
    }
}

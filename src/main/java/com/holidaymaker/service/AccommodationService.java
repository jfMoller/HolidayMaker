package com.holidaymaker.service;
import com.holidaymaker.entity.Accommodation;
import com.holidaymaker.utility.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccommodationService {

    private final Connection connection;

    private final Scanner scanner;

    public AccommodationService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }
    public List<Accommodation> getAllAccommodations() throws SQLException {

        String sql = "SELECT * FROM accommodations";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Accommodation> accommodationTypes = new ArrayList<>();

        while (resultSet.next()) {
            accommodationTypes.add(new Accommodation(resultSet));
        }

        return accommodationTypes;
    }


}

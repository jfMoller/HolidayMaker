package com.holidaymaker.service;

import com.holidaymaker.entity.AdditionalService;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdditionalServicesService {

    private final Connection connection;

    public AdditionalServicesService() {
        this.connection = ConnectionProvider.getConnection();
    }

    public List<AdditionalService> getAdditionalServices() throws SQLException {

        String sql = "SELECT * FROM additional_services";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<AdditionalService> additionalServices = new ArrayList<>();

        while (resultSet.next()) {
            additionalServices.add(new AdditionalService(resultSet));
        }

        return additionalServices;
    }

    public void addAdditionalService(AdditionalService additionalService) throws SQLException {
        String sql = "INSERT INTO additional_services (price, description)" +
                " VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDouble(1, additionalService.getPrice());
        statement.setString(2, additionalService.getDescription());

        statement.executeUpdate();
    }

}

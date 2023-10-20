package com.holidaymaker.service;

import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TravelPackageService {
    private final Connection connection;

    private final Scanner scanner;

    public TravelPackageService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }

    public void viewTravelPackage() throws SQLException {
        List<TravelPackage> travelPackages = getTravelPackage();
        for (TravelPackages travelPackage : travelPackages) {
            System.out.println(travelPackage);
        }
    }
}

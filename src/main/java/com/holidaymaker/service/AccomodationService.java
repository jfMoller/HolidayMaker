package com.holidaymaker.service;
import com.holidaymaker.utility.ConnectionProvider;

import java.sql.Connection;
import java.util.Scanner;

public class AccomodationService {

    private final Connection connection;

    private final Scanner scanner;

    public AccomodationService() {
        this.connection = ConnectionProvider.getConnection();
        this.scanner = new Scanner(System.in);
    }
}

package com.holidaymaker;

import com.holidaymaker.utility.ConnectionProvider;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        ConnectionProvider.openConnection();
        Menu menu = new Menu();
        menu.launch();
        ConnectionProvider.closeConnection();
    }
}

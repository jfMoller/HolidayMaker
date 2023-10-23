package com.holidaymaker;

import com.holidaymaker.entity.TravelPackage;
import com.holidaymaker.service.CustomerService;
import com.holidaymaker.service.TravelPackageService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    private final CustomerService customerService;
    private final TravelPackageService travelPackageService;

    public Menu() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
        travelPackageService = new TravelPackageService();
    }

    public void launch() throws SQLException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. View customers:");
            System.out.println("2. Add customer");
            System.out.println("3. View travel packages");
            System.out.println("4. Quit");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> customerService.viewCustomers();
                case 2 -> customerService.addMenuCustomer();
                case 3 -> travelPackageService.viewTravelPackage();
                case 4 -> {
                    System.out.println("Shutting down...");
                    return;
                }
                default -> System.out.println("Wrong input, try again.");
            }

        }
    }
}

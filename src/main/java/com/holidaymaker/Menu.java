package com.holidaymaker;

import com.holidaymaker.service.BookingService;
import com.holidaymaker.service.CustomerService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    private final CustomerService customerService;
    private final BookingService bookingService;

    public Menu() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
        bookingService = new BookingService();
    }

    public void launch() throws SQLException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. View Customers:");
            System.out.println("2. Add Customer");
            System.out.println("3. View Bookings");
            System.out.println("4. Verify Booking Payment");
            System.out.println("5. Quit");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> customerService.viewCustomers();
                case 2 -> customerService.addMenuCustomer();
                case 3 -> bookingService.viewBookings();
                case 4 -> bookingService.showVerifyPayment();
                case 5 -> {
                    System.out.println("Shutting down...");
                    return;
                }
                default -> System.out.println("Wrong input, try again.");
            }

        }
    }
}

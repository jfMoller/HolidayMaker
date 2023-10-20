package com.holidaymaker.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Accomodation {

    private int id;
    private static final List<String> types = new ArrayList<>(List.of("Hotel", "Hostal", "Cottage"));
    private double price;
    private int numberOfBeds;
    private int travelPackage;
    private String type;

    public Accomodation( double price, int numberOfBeds) {
        this.price = price;
        this.numberOfBeds = numberOfBeds;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<String> getAllTypes() { return types; }

    public double getPrice() { return price; }

    public int getNumberOfBeds() { return numberOfBeds; }

    public int getTravelPackage() { return travelPackage; }
}

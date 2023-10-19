package com.holidaymaker.entity;

public class Accomodation {

    private int id;
    private String type;
    private String price;
    private String peopleHoused;
    private int travelPackage;

    public Accomodation(String type, String price, String peopleHoused, int travelPackage) {
        this.type = type;
        this.price = price;
        this.peopleHoused = peopleHoused;
        this.travelPackage = travelPackage;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getPeopleHoused() {
        return peopleHoused;
    }
    public String getTravelPackage() {
        return travelPackage;
    }
}

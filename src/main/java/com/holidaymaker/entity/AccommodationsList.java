package com.holidaymaker.entity;

import java.util.ArrayList;
import java.util.List;

public class AccommodationsList {
    List<AccommodationListItem> accommodations;

    public AccommodationsList() {
        this.accommodations = new ArrayList<>();
    }

    public void addListItem(AccommodationListItem item) {
        accommodations.add(item);
    }

    public List<AccommodationListItem> getAccommodations() {
        return accommodations;
    }

    public String buildList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AccommodationListItem item : accommodations) {
            stringBuilder.append("type: " + item.type() + ", price: " + item.price() + ", Number of beds: " + item.numberOfBeds() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return buildList();
    }

    public double getTotalPrice() {
        double price = 0;
        for (AccommodationListItem item : accommodations) {
            price += item.price();
        }
        return price;
    }
}

package com.holidaymaker.entity;

import java.util.ArrayList;
import java.util.List;

public class AdditionalServicesList {
    List<AdditionalServiceListItem> additionalServices;

    public AdditionalServicesList() {
        this.additionalServices = new ArrayList<>();
    }

    public void addListItem(AdditionalServiceListItem item) {
        additionalServices.add(item);
    }

    public List<AdditionalServiceListItem> getAdditionalServices() {
        return additionalServices;
    }

    public String buildList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AdditionalServiceListItem item : additionalServices) {
            stringBuilder.append("price: " + item.price() + ", description: " + item.description() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return buildList();
    }

    public double getTotalPrice() {
        double price = 0;
        for (AdditionalServiceListItem item : additionalServices) {
            price += item.price();
        }
        return price;
    }
}

package com.holidaymaker.entity;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesList {
    List<ActivityListItem> activities;

    public ActivitiesList() {
        this.activities = new ArrayList<>();
    }

    public void addListItem(ActivityListItem item) {
        activities.add(item);
    }

    public List<ActivityListItem> getActivities() {
        return activities;
    }

    public String buildList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ActivityListItem item : activities) {
            stringBuilder.append("\n\t - " + item.type() + ", price: " + item.price());
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return buildList();
    }

    public double getTotalPrice() {
        double price = 0;
        for (ActivityListItem item : activities) {
            price += item.price();
        }
        return price;
    }
}

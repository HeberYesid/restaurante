package com.example.patterns.behavioral.iterator;

public class DailySpecial {
    private String name;
    private String category;
    private double price;
    private String day;

    public DailySpecial(String name, String category, double price, String day) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f - %s", name, category, price, day);
    }
}

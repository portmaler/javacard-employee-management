package com.sisbd.confirmationdb.models;

import java.math.BigDecimal;

public class Job {
    private int id;
    private String title;
    private String description;
    private double price;

    public Job(int id, String title, String description, double price) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

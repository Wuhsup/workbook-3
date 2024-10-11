package com.pluralsight.store;

public class Products {
    private String id;
    private String name;
    private double price;
    private String department;

    public Products(String id, String name, double price, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.price = price;

    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getPrice() { return price; }
   // public int getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("SKU: %s, Name: %s, Price: $%.2f, Department: %s", id, name, price, department);
    }
}


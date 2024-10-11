package com.pluralsight.store;

import java.util.ArrayList;
import java.util.List;

public class MyBag {
    private List<Products> items = new ArrayList<>();

    public void addProduct(Products product) { items.add(product); }

    public void removeProduct(String Id) {
        items.removeIf(product -> product.getId().equals(Id));
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            items.forEach(System.out::println);
            System.out.printf("Total: $%.2f%n", getTotalAmount());
        }
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(Products::getPrice).sum();
    }

    public void clear() { items.clear(); }
}


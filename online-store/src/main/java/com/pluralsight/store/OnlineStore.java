package com.pluralsight.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineStore {
    private List<Products> item = new ArrayList<>();
    private MyBag cart = new MyBag();
    private Scanner scanner = new Scanner(System.in);

    public OnlineStore() {
        loadInventory("./src/main/resources/products.csv");
    }

    private void loadInventory(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                item.add(new Products(
                        values[0],
                        values[1],
                        Double.parseDouble(values[2]),
                        values[3]
                ));
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void run() {
        while (true) {
            displayHomeScreen();
            int choice = getUserChoice(3);
            switch (choice) {
                case 1 -> displayProducts();
                case 2 -> displayCart();
                case 3 -> {
                    System.out.println("Thank you for shopping with us!");
                    return;
                }
            }
        }
    }

    private void displayHomeScreen() {
        System.out.println("\n--- Online Store Home ---\n1. Display Products\n2. Display Cart\n3. Exit\nEnter your choice: ");
    }

    private int getUserChoice(int maxChoice) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice < maxChoice) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.print("Invalid choice. Please try again: ");
        }
    }

    private void displayProducts() {
        System.out.println("\n--- Product Catalog ---");
        item.forEach(System.out::println);
       // System.out.println(loadInventory("./src/main/resources/product.csv"));
        loadInventory("./src/main/resources/products.csv");

        switch (getUserChoice(3)) {
            case 1 -> searchProducts();
            case 2 -> addToCart();
        }
    }

    private void searchProducts() {
        System.out.print("Enter search term: ");
        String term = scanner.nextLine().toLowerCase();
        System.out.println("\n--- Search Results ---");
        item.stream()
                .filter(product -> product.getName().toLowerCase().contains(term) ||
                        product.getDepartment().toLowerCase().contains(term) ||
                        String.valueOf(product.getPrice()).contains(term))
                .forEach(System.out::println);
    }

    private void addToCart() {
        System.out.print("Enter product ID to add to cart: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            item.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .ifPresentOrElse(product -> {
                        cart.addProduct(product);
                        System.out.println("Product added to cart.");
                    }, () -> System.out.println("Product not found."));
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private void displayCart() {
        System.out.println("\n--- Your Shopping Cart ---");
        cart.displayCart();
        System.out.println("\n1. Check Out\n2. Remove Product\n3. Go Back\nEnter your choice: ");

        switch (getUserChoice(3)) {
            case 1 -> checkOut();
            case 2 -> removeFromCart();
        }
    }

    private void checkOut() {
        System.out.printf("Thank you for your purchase! Total: $%.2f%n", cart.getTotalAmount());
        cart.clear();
    }

    private void removeFromCart() {
        System.out.print("Enter product ID to remove from cart: ");
        try {
            String id = (scanner.nextLine());
            cart.removeProduct(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    public static void main(String[] args) {
        new OnlineStore().run();
    }
}

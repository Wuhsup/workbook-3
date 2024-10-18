package com.pluralsight.com.accounting;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//2024-10-16|Payment|-25.0|school books
//2024-10-16| Payment |-25.0| halloween candy
//2024-10-16| Deposit |1500.0| tax return
// 2024-10-16| Deposit |500.0| bingo wins
//2024-10-16| Payment |-50.0| phone bill
//2024-10-16| Deposit |150.5| YUU wages
//2024-10-16| Deposit |20.0| Type C- Adapter










// Main class to handle the transaction ledger application
public class Main {
    private static final String FILENAME = "src/main/resources/transactions.csv"; // Path to the CSV file for transactions
    private static ArrayList<Transaction> transactions = new ArrayList<>(); // List to hold all transactions
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input


    // Main method to start the application
    public static void main(String[] args) {

    System.out.println(Transaction.getTime());

        loadTransactions();
      //  new Main().run(); // Create an instance of Main and run the application

        run(); //call run method to start the app
    }
    // Constructor to initialize the application and load existing transactions
    public Main() {

    } // we do not need a constructor on the main class because we are not creating an onstance of the main

    // Loads transactions from a CSV file into the transactions list
    private static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            reader.readLine(); // Skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the CSV line into parts
                LocalDate date = LocalDate.parse(parts[0].trim());
                LocalTime time = LocalTime.parse(parts[1].trim());
                // Parse the date
                // Add new Transaction to the list
                transactions.add(new Transaction(date, time, parts[2], Double.parseDouble(parts[3]), parts[4]));
            }
        } catch (IOException e) {
            System.out.println("No existing transactions file found. Starting with an empty ledger.");
        }
        //sortTransactions(); // Uncomment to enable sorting of transactions
    }

    // Saves all transactions back to the CSV file
    private static void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write("Date  |  Time  |  Type  |  Amount  |  Description  "); // Write header
            writer.newLine(); // Move to the next line
            for (Transaction t : transactions) {
                writer.write(t.toString()); // Write each transaction
                writer.newLine(); // Move to the next line
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Adds a new transaction to the list and saves it to the file
    public static void addTransaction(String type, double amount, String description) {
        transactions.add(0, new Transaction(LocalDate.now(), LocalTime.now(), type, amount, description)); // Add at the beginning
        saveTransactions(); // Save to file
    }

    // Displays transactions, filtered by type if specified
    public static void displayTransactions(String filterType) {
        for (Transaction t : transactions) {
            if (filterType == null || t.getType().equals(filterType)) {
                System.out.printf("%s | %s | $%.2f | %s%n",
                        t.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), t.getType(), t.getAmount(), t.getDescription());
            }
        }
    }

    // Runs a report based on the selected report type
    public static void runReport(String reportType) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate startDate = null; // Initialize start date
        LocalDate endDate = today; // Initialize end date to today

        // Determine date range based on report type
        switch (reportType) {
            case "1": startDate = today.withDayOfMonth(1); break; // Month To Date
            case "2": startDate = today.minusMonths(1).withDayOfMonth(1); endDate = today.withDayOfMonth(1).minusDays(1); break; // Previous Month
            case "3": startDate = today.withDayOfYear(1); break; // Year To Date
            case "4": startDate = today.minusYears(1).withDayOfYear(1); endDate = today.withDayOfYear(1).minusDays(1); break; // Previous Year
        }

        // Display transactions within the date range
        for (Transaction t : transactions) {
            if ((startDate == null || !t.getDate().isBefore(startDate)) && !t.getDate().isAfter(endDate)) {
                System.out.printf("%s | %s | $%.2f | %s%n", t.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), t.getType(), t.getAmount(), t.getDescription());
            }
        }
    }

    // Searches transactions by vendor name
    public static void searchByVendor(String vendor) {
        transactions.stream() // Grab and process object in that collection.
                .filter(t -> t.getDescription().toLowerCase().contains(vendor.toLowerCase())) // Case insensitive search
                .forEach(t -> System.out.printf("%s | %s | $%.2f | %s%n", t.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE), t.getType(), t.getAmount(), t.getDescription()));
    }

    // Main application loop for user interaction
    public static void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Home Screen ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            // Handle user choices
            switch (choice) {
                case "D": handleTransaction("Deposit"); break; // Handle deposit
                case "P": handleTransaction("Payment"); break; // Handle payment
                case "L": runLedgerScreen(); break; // Open ledger screen
                case "X": System.out.println("Exiting application. Goodbye!"); running = false; break; // Exit
                default: System.out.println("Invalid choice. Please try again."); // Invalid input
            }
        }
    }

    // Handles the addition of a transaction based on type
    private static void handleTransaction(String type) {
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Read amount
        System.out.print("Enter description: ");
        String description = scanner.nextLine(); // Read description
        addTransaction(type, type.equals("Payment") ? -amount : amount, description); // Add transaction
    }

    // Manages the ledger screen where users can view transactions
    private static void runLedgerScreen() {
        boolean inLedger = true;
        while (inLedger) {
            System.out.println("\n--- Ledger Screen ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            // Handle ledger choices
            switch (choice) {
                case "A": displayTransactions(null); break; // Display all transactions
                case "D": displayTransactions("Deposit"); break; // Display only deposits
                case "P": displayTransactions("Payment"); break; // Display only payments
                case "R": runReportScreen(); break; // Open report screen
                case "H": inLedger = false; break; // Go back to home
                default: System.out.println("Invalid choice. Please try again."); // Invalid input
            }
        }
    }

    // Manages the report screen for user selection
    private static void runReportScreen() {
        boolean inReports = true;
        while (inReports) {
            System.out.println("\n--- Report Screen ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            // Handle report choices
            switch (choice) {
                case "1": runReport("1"); break; // Month To Date
                case "2": runReport("2"); break; // Previous Month
                case "3": runReport("3"); break; // Year To Date
                case "4": runReport("4"); break; // Previous Year
                case "5":
                    System.out.print("Enter vendor name: ");
                    searchByVendor(scanner.nextLine()); // Search by vendor
                    break;
                case "0": inReports = false; break; // Go back to ledger
                default: System.out.println("Invalid choice. Please try again."); // Invalid input
            }
        }
    }


}

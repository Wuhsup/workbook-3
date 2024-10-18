package com.pluralsight.com.accounting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private static LocalTime time;
    private String type;
    private double amount;
    private String description;

    public Transaction(){

    }

    public Transaction(LocalDate date, LocalTime time, String type, double amount, String description) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public static LocalTime getTime() {
        return time;

    }
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "|" + time.format(DateTimeFormatter.ofPattern("hh:mm")) + "|" + type + "|" + amount + "|" + description;
    }
}










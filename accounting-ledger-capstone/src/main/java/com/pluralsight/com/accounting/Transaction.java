package com.pluralsight.com.accounting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private static LocalTime time;
    private String vendor;
    private double amount;
    private String description;


    public Transaction(LocalDate date, LocalTime time, String vendor, double amount, String description) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.amount = amount;
        this.description = description;
    }


    public LocalDate getDate() {
        return date;
    } //Getter

    public static LocalTime getTime() {
        return time; //Getter

    }
    public String getVendor() {
        return vendor; //Getter
    }

    public double getAmount() {
        return amount; //Getter
    }

    public String getDescription() {
        return description; //Getter
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "|" + time.format(DateTimeFormatter.ofPattern("hh:mm")) + "|" + vendor + "|" + amount + "|" + description;
    }
}










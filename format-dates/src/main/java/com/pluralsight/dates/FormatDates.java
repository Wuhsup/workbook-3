package com.pluralsight.dates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId; //currently used. Used to be TimeZone

public class FormatDates {

    public static void main(String[] args) {

        LocalDateTime today = LocalDateTime.now();

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        DateTimeFormatter fmt4 = DateTimeFormatter.ofPattern("E, MMM d, yyyy HH:mm 'GMT'");

        System.out.println(today.format(fmt1));
        System.out.println(today.format(fmt2));
        System.out.println(today.format(fmt3));
        System.out.println(today.atZone(ZoneId.of("GMT")).format(fmt4));

        DateTimeFormatter localFmt = DateTimeFormatter.ofPattern("h:mm a 'on' dd-MMM-yyyy");
        System.out.println(today.atZone(ZoneId.systemDefault()).format(localFmt));
    }
}

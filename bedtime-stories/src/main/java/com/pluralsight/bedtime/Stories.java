package com.pluralsight.bedtime;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Stories {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("""
               =========================================
                Story Time! Select A Story To Read =)
                1. goldilocks
                2. hansel_and_gretel
                3. mary_had_a_little_lamb
               ==========================================
               """);
        String storySelection = inputScanner.nextLine().trim();
        String storyFileName = "";


        switch (storySelection) {
            case "1":
                storyFileName = "goldilocks.txt";
                break;
            case "2":
                storyFileName = "hansel_and_gretel.txt";
                break;
            case "3":
                storyFileName = "mary_had_a_little_lamb.txt";
                break;
            default:
                System.out.println("Invalid choice. Please select a valid story number.");
                inputScanner.close();
                return;
        }

        storyReader(storyFileName);

        inputScanner.close();
    }

    private static void storyReader(String storyFileName) {
        try (FileReader fileReader = new FileReader("./src/main/resources/" + storyFileName);
             Scanner fileScanner = new Scanner(fileReader)) {


            int lineNumber = 1;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("The bedtime story " + storyFileName + " doesn't exist. Check the spelling of the name and try again.");
        }
    }
}

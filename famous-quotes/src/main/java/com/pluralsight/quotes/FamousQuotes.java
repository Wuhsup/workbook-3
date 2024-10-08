package com.pluralsight.quotes;

import java.util.Random;
import java.util.Scanner;

public class FamousQuotes {
    public static void main(String[] args) {

        String[] quotes = {
                "It’s not about how you stand by your car, it’s about how you race your car. - Brian O'Conner",
                "I don’t have friends. I have family. - Dominic Toretto",
                "We ride together, we die together. Bad boys for life! - Mike and Marcus",
                "I want to be a ghost, but one that’s seen. - Cataleya Restrepo ",
                "You can’t take revenge for someone else. You have to make it your own. - Cataleya Restrpo",
                "You only live once, but if you do it right, once is enough. - Mae West",
                "The time is always right to do what is right - Martin Luther King Jr",
                "The only way to do great work is to love what you do. - Steve Jobs",
                "Madness, as you know, is like gravity. All it takes is a little push. - Joker",
                "Introduce a little anarchy. Upset the established order, and everything becomes chaos. - Joker"
        };

        Scanner userInput = new Scanner(System.in);
        Random random = new Random();
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("Pick A Number, Any Number! (Make it 1-10)");


            int choice = userInput.nextInt();

            if (choice == 0) {

                int randomIndex = random.nextInt(quotes.length);
                System.out.println("Random Quote: " + quotes[randomIndex]);
            } else if (choice >= 1 && choice <= 10) {

                System.out.println("Quote: " + quotes[choice - 1]);
            } else {
                System.out.println("Enter A Number 1-10");
            }

            System.out.println("View Another Quote! Or not ..  (yes/no)");
            String response = userInput.next().toLowerCase();
            if (!response.equals("yes")) {
                continueProgram = false;
            }
        }

        userInput.close();
    }
}


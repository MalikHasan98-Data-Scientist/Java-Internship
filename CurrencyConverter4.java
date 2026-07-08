// Currency Converter - Project 4
// Converts between different currencies using predefined exchange rates

import java.util.Scanner;
import java.text.DecimalFormat;

public class CurrencyConverter {

    // Exchange rates relative to USD (as of July 2026)
    // Source: Based on current market rates
    private static final double USD_TO_EUR = 0.88;
    private static final double USD_TO_GBP = 0.74;
    private static final double USD_TO_JPY = 157.50;
    private static final double USD_TO_CAD = 1.36;
    private static final double USD_TO_AUD = 1.48;
    private static final double USD_TO_CHF = 0.89;
    private static final double USD_TO_INR = 83.25;

    // Currency codes for display
    private static final String[] CURRENCIES = {
            "USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "INR"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        boolean keepRunning = true;

        System.out.println("=================================");
        System.out.println("   CURRENCY CONVERTER");
        System.out.println("=================================\n");

        while (keepRunning) {
            // Display menu
            System.out.println("Available currencies:");
            for (int i = 0; i < CURRENCIES.length; i++) {
                System.out.println((i + 1) + ". " + CURRENCIES[i]);
            }
            System.out.println("0. Exit\n");

            // Get base currency
            System.out.print("Select BASE currency (enter number): ");
            int baseChoice = getValidIntInput(scanner, 0, CURRENCIES.length);

            if (baseChoice == 0) {
                System.out.println("\nThanks for using Currency Converter. Goodbye!");
                break;
            }
            // Get target currency
            System.out.print("Select TARGET currency (enter number): ");
            int targetChoice = getValidIntInput(scanner, 0, CURRENCIES.length);

            if (targetChoice == 0) {
                System.out.println("\nThanks for using Currency Converter. Goodbye!");
                break;
            }
            // Check if same currency
            if (baseChoice == targetChoice) {
                System.out.println("\nError: Cannot convert a currency to itself!\n");
                continue;
            }
            // Get amount to convert
            System.out.print("\nEnter amount to convert: ");
            double amount = getValidDoubleInput(scanner);

            // Perform conversion
            String baseCurrency = CURRENCIES[baseChoice - 1];
            String targetCurrency = CURRENCIES[targetChoice - 1];
            double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

            // Display result
            System.out.println("\n---------------------------------");
            System.out.println("Conversion Result:");
            System.out.println(df.format(amount) + " " + baseCurrency + " = " +
                    df.format(convertedAmount) + " " + targetCurrency);
            System.out.println("---------------------------------\n");

            // Ask if user wants to continue
            System.out.print("Do another conversion? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("no") || response.equals("n")) {
                keepRunning = false;
                System.out.println("\nThanks for using Currency Converter. Goodbye!");
            }
            System.out.println();
        }
        scanner.close();
    }
     // Converts amount from base currency to target currency
     // Uses USD as the base currency for all conversions

    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // First convert to USD, then convert USD to target currency
        double amountInUSD = convertToUSD(amount, fromCurrency);
        return convertFromUSD(amountInUSD, toCurrency);
    }
     // Converts any currency to USD
    private static double convertToUSD(double amount, String currency) {
        switch (currency) {
            case "USD":
                return amount;
            case "EUR":
                return amount / USD_TO_EUR;
            case "GBP":
                return amount / USD_TO_GBP;
            case "JPY":
                return amount / USD_TO_JPY;
            case "CAD":
                return amount / USD_TO_CAD;
            case "AUD":
                return amount / USD_TO_AUD;
            case "CHF":
                return amount / USD_TO_CHF;
            case "INR":
                return amount / USD_TO_INR;
            default:
                return amount;
        }
    }
     // Converts USD to target currency
    private static double convertFromUSD(double amountInUSD, String currency) {
        switch (currency) {
            case "USD":
                return amountInUSD;
            case "EUR":
                return amountInUSD * USD_TO_EUR;
            case "GBP":
                return amountInUSD * USD_TO_GBP;
            case "JPY":
                return amountInUSD * USD_TO_JPY;
            case "CAD":
                return amountInUSD * USD_TO_CAD;
            case "AUD":
                return amountInUSD * USD_TO_AUD;
            case "CHF":
                return amountInUSD * USD_TO_CHF;
            case "INR":
                return amountInUSD * USD_TO_INR;
            default:
                return amountInUSD;
        }
    }
     // Helper method to get valid integer input from user
    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
     // Helper method to get valid double input from user
    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input > 0) {
                    return input;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid amount: ");
            }
        }
    }
}
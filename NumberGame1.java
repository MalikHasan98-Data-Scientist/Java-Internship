//Project 1: The Number Game

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {

        Scanner playerInput = new Scanner(System.in);
        Random numberGenerator = new Random();        //Random to pick the secret number.

        // Tracking the player's journey
        int totalScore = 0;
        int roundsPlayed = 0;
        boolean wantsToPlayAgain = true;

        // --- The Welcome Screen
        System.out.println("\n=================================================");
        System.out.println("           Welcome to the Number Game! ");
        System.out.println("=================================================");
        System.out.println("I'm going to think of a secret number between 1 and 100.");
        System.out.println("You have 7 chances to guess it. I'll give you hints along the way.");
        System.out.println("Ready? Let's dive in!\n");

        // --- The Main Game Loop
        while (wantsToPlayAgain) {
            roundsPlayed++;

            // Pick a new secret number for this round (1 to 100)
            int secretNumber = numberGenerator.nextInt(100) + 1;
            int maxAttempts = 7;
            int attemptsUsed = 0;
            boolean guessedIt = false;

            System.out.println(" --- Starting Round " + roundsPlayed + " --- ");
            System.out.println("Alright, I've got my number. What's your first guess?");

            // --- The Guessing Loop
            while (attemptsUsed < maxAttempts) {
                int attemptsLeft = maxAttempts - attemptsUsed;
                System.out.print("--> Take a guess (" + attemptsLeft + " tries left): ");

                int playerGuess;

                // Getting player input while handling excpetions
                try {
                    playerGuess = playerInput.nextInt();
                } catch (InputMismatchException e) {

                    System.out.println("Oops! That doesn't look like a whole number. Let's try again!");
                    playerInput.next();       // Clear previous bad input from memory
                    continue;                // Skip the rest of the loop and ask again without using up an attempt
                }

                attemptsUsed++;

                // --- Evaluating the Guess ---
                if (playerGuess < 1 || playerGuess > 100) {    // If they go out of bounds
                    System.out.println("Remember, keep your guess between 1 and 100! (Attempt still counts)");
                }

                if (playerGuess == secretNumber) {
                    guessedIt = true;

                    // The faster they guess, the higher the score.
                    // 1st try = 70 pts, 2nd try = 60 pts... 7th try = 10 pts.

                    int roundScore = (maxAttempts - attemptsUsed + 1) * 10;
                    totalScore += roundScore;

                    System.out.println("\n🎉 BOOM! You got it! The number was indeed " + secretNumber + ".");
                    System.out.println("🏆 You nailed it in " + attemptsUsed + " attempts and earned " + roundScore + " points!");
                    break;        // Break out of the guessing loop since they won

                } else if (playerGuess < secretNumber) {
                    System.out.println("Ooh, a bit too low. Maybe try going higher next time!");
                } else {
                    System.out.println("Whoa, a bit too high! Bring it down a little.");
                }

                // If they used their last attempt and didn't guess
                if (attemptsUsed == maxAttempts && !guessedIt) {
                    System.out.println("\nAh, you ran out of tries! Better luck next time.");
                    System.out.println("(The secret number was " + secretNumber + ")");
                }
            }

            // --- End of Round Summary
            System.out.println("\nYour current total score is: " + totalScore);

            // Asking to play again
            System.out.print("\nThat was fun! Want to go another round? (yes/no): ");
            String response = playerInput.next();

            // We accept "yes", "y", "yeah", etc. Anything else stops the game.
            if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("y")) {
                wantsToPlayAgain = false;
            }
        }

        // --- The Final Farewell ---
        System.out.println("\n=================================================");
        System.out.println("             🏁 GAME OVER 🏁                    ");
        System.out.println("=================================================");
        System.out.println("Thanks for playing! Here are your final stats:");
        System.out.println(" Total Rounds Played: " + roundsPlayed);
        System.out.println(" Final Total Score:   " + totalScore);
        System.out.println("=================================================");

        playerInput.close();
    }
}
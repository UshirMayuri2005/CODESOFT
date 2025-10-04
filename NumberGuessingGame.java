import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        int roundsWon = 0;
        int roundNumber = 1;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            System.out.println("\n=== Round " + roundNumber + " ===");
            int[] result = playRound(scanner);
            boolean won = result[0] == 1;
            int score = result[1];
            totalScore += score;
            if (won) {
                roundsWon++;
            }

            System.out.printf("Current score: %d | Rounds won: %d/%d%n", 
                totalScore, roundsWon, roundNumber);

            System.out.print("\nWould you like to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
            roundNumber++;
        }

        System.out.println("\nFinal Results:");
        System.out.printf("Total score: %d%n", totalScore);
        System.out.printf("Rounds won: %d out of %d%n", roundsWon, roundNumber);
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static int[] playRound(Scanner scanner) {
        Random random = new Random();
        int target = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        int attempts = 0;
        int score = 0;

        System.out.printf("New round! I'm thinking of a number between %d and %d. You have %d attempts.%n",
                MIN_RANGE, MAX_RANGE, MAX_ATTEMPTS);

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            try {
                int guess = scanner.nextInt();
                attempts++;

                if (guess < MIN_RANGE || guess > MAX_RANGE) {
                    System.out.printf("Please enter a number between %d and %d.%n", MIN_RANGE, MAX_RANGE);
                    continue;
                }

                if (guess == target) {
                    System.out.printf("Congratulations! You guessed the number in %d attempts!%n", attempts);
                    // Score: 100 points base, minus 10 points per attempt used
                    score = Math.max(0, 100 - (attempts - 1) * 10);
                    return new int[]{1, score};
                } else if (guess < target) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                System.out.printf("Attempts remaining: %d%n", MAX_ATTEMPTS - attempts);

            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        System.out.printf("Game over! The number was %d.%n", target);
        return new int[]{0, 0};
    }
}
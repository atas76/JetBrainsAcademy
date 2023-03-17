package projects.rockpaperscissors;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";
    private static final String ROCK = "rock";

    private static final String [] CHOICES = { PAPER, SCISSORS, ROCK };

    private static final Map<String, String> winningOrderMap = Map.ofEntries(
            Map.entry(PAPER, ROCK),
            Map.entry(SCISSORS, PAPER),
            Map.entry(ROCK, SCISSORS)
    );

    private static final Map<String, String> losingOrderMap = Map.ofEntries(
            Map.entry(SCISSORS, ROCK),
            Map.entry(ROCK, PAPER),
            Map.entry(PAPER, SCISSORS)
    );

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String playerChoice = scanner.next();

        while (!"!exit".equals(playerChoice)) {
            String currentPlayerChoice = playerChoice;
            if (Arrays.stream(CHOICES).noneMatch(choice -> choice.equals(currentPlayerChoice))) {
                System.out.println("Invalid input");
                playerChoice = scanner.next();
                continue;
            }
            String computerChoice = getComputerChoice(playerChoice);
            displayResult(compare(playerChoice, computerChoice), computerChoice);
            playerChoice = scanner.next();
        }

        System.out.println("Bye!");
    }

    private static void displayResult(int result, String computerOption) {
        switch (result) {
            case -1 -> System.out.println("Sorry, but the computer chose " + computerOption);
            case 0 -> System.out.println("There is a draw (" + computerOption + ")");
            case 1 -> System.out.println("Well done. The computer chose " + computerOption + " and failed");
        }
    }

    private static int compare(String choiceA, String choiceB) {
        if (choiceB.equals(winningOrderMap.get(choiceA))) {
            return 1;
        }
        if (choiceB.equals(losingOrderMap.get(choiceA))) {
            return -1;
        }
        return 0;
    }

    private static String getComputerChoice(String playerChoice) {
        return CHOICES[new Random().nextInt(3)];
    }
}

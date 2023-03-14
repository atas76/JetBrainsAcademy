package projects.rockpaperscissors;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";
    private static final String ROCK = "rock";

    private static Map<String, String> winningOrderMap = Map.ofEntries(
            Map.entry(PAPER, ROCK),
            Map.entry(SCISSORS, PAPER),
            Map.entry(ROCK, SCISSORS)
    );

    private static Map<String, String> losingOrderMap = Map.ofEntries(
            Map.entry(SCISSORS, ROCK),
            Map.entry(ROCK, PAPER),
            Map.entry(PAPER, SCISSORS)
    );

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String playerChoice = scanner.next();

        String computerChoice = getComputerChoice(playerChoice);
        int comparisonResult = compare(playerChoice, computerChoice);
        if (comparisonResult > 0) {
            System.out.println("Sorry, but the computer chose " + computerChoice);
        } else {
            System.out.println("Error: this is not part of the script yet");
        }
    }

    private static int compare(String choiceA, String choiceB) {
        if (choiceB.equals(winningOrderMap.get(choiceA))) {
            return -1;
        }
        if (choiceB.equals(losingOrderMap.get(choiceA))) {
            return 1;
        }
        return 0;
    }

    private static String getComputerChoice(String playerChoice) {
        return losingOrderMap.get(playerChoice);
    }
}

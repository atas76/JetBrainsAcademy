package projects.rockpaperscissors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

        Map<String, Integer> scores = readScores();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.next();
        int playerScore = scores.getOrDefault(playerName, 0);

        System.out.println("Hello, " + playerName);

        String playerChoice = scanner.next();

        while (!"!exit".equals(playerChoice)) {
            String currentPlayerChoice = playerChoice;
            if (playerChoice.startsWith("!")) {
                if ("!rating".equals(playerChoice)) { // To be replaced with switch in future iterations
                    System.out.println("Your rating: " + playerScore);
                } else {
                    System.out.println("Invalid input");
                }
                playerChoice = scanner.next();
                continue;
            }
            if (Arrays.stream(CHOICES).noneMatch(choice -> choice.equals(currentPlayerChoice))) {
                System.out.println("Invalid input");
                playerChoice = scanner.next();
                continue;
            }
            String computerChoice = getComputerChoice(playerChoice);
            int result = compare(playerChoice, computerChoice);
            playerScore += 50 + result * 50;
            displayResult(result, computerChoice);
            playerChoice = scanner.next();
        }

        System.out.println("Bye!");

    }

    private static Map<String, Integer> readScores() {
        Map<String, Integer> scores = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("rating.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String [] currentRecord = line.split("\s");
                scores.put(currentRecord[0], Integer.valueOf(currentRecord[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scores;
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

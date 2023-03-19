package projects.rockpaperscissors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";
    private static final String ROCK = "rock";

    private static String [] gameChoices = { SCISSORS, PAPER, ROCK };

    private static Map<String, Set<String>> winningOrderMap = Map.ofEntries(
            Map.entry(PAPER, Set.of(ROCK)),
            Map.entry(SCISSORS, Set.of(PAPER)),
            Map.entry(ROCK, Set.of(SCISSORS))
    );

    private static Map<String, Set<String>> losingOrderMap = Map.ofEntries(
            Map.entry(SCISSORS, Set.of(ROCK)),
            Map.entry(ROCK, Set.of(PAPER)),
            Map.entry(PAPER, Set.of(SCISSORS))
    );

    public static void main(String[] args) {

        Map<String, Integer> scores = readScores();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        int playerScore = scores.getOrDefault(playerName, 0);

        System.out.println("Hello, " + playerName);

        String gameChoicesInput = scanner.nextLine();

        if (!gameChoicesInput.isEmpty()) {
            gameChoices = gameChoicesInput.split(",");
            createWinningLosingOrders();
        }

        System.out.println("Okay, let's start");

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
            if (Arrays.stream(gameChoices).noneMatch(choice -> choice.equals(currentPlayerChoice))) {
                System.out.println("Invalid input");
                playerChoice = scanner.next();
                continue;
            }
            String computerChoice = getComputerChoice();
            int result = compare(playerChoice, computerChoice);
            playerScore += 50 + result * 50;
            displayResult(result, computerChoice);
            playerChoice = scanner.next();
        }

        System.out.println("Bye!");

    }

    private static void createWinningLosingOrders() {
        winningOrderMap = new HashMap<>();
        losingOrderMap = new HashMap<>();
        for (int i = 0; i < gameChoices.length; i++) {
            int offsetBoundary = i + gameChoices.length / 2;
            for (int j = i + 1; j < Math.min(gameChoices.length, offsetBoundary + 1); j++) {
                addWinningOrder(j, i);
            }
            for (int j = 0; j < offsetBoundary - gameChoices.length + 1; j++) {
                addWinningOrder(j, i);
            }
        }
    }

    private static void addWinningOrder(int winningIndex, int losingIndex) {
        winningOrderMap.putIfAbsent(gameChoices[winningIndex], new HashSet<>());
        losingOrderMap.putIfAbsent(gameChoices[losingIndex], new HashSet<>());
        winningOrderMap.get(gameChoices[winningIndex]).add(gameChoices[losingIndex]);
        losingOrderMap.get(gameChoices[losingIndex]).add(gameChoices[winningIndex]);
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
        if (winningOrderMap.get(choiceA).contains(choiceB)) {
            return 1;
        }
        if (losingOrderMap.get(choiceA).contains(choiceB)) {
            return -1;
        }
        return 0;
    }

    private static String getComputerChoice() {
        return gameChoices[new Random().nextInt(gameChoices.length)];
    }
}

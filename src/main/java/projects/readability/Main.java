package projects.readability;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String text = readFile(args[0]);

        String [] sentences = text.split("[.!?]");
        int sentenceNumber = sentences.length;
        int wordNumber = 0;
        int characterNumber = 0;
        int syllableNumber = 0;
        int polysyllables = 0;

        for (int i = 0; i < sentences.length; i++) {
            String [] words = sentences[i].trim().split("\\s");
            wordNumber += words.length;
            for (int j = 0; j < words.length; j++) {
                characterNumber += words[j].length();
                int currentWordSyllables = getNumberOfSyllables(words[j]);
                syllableNumber += currentWordSyllables;
                if (currentWordSyllables > 2) ++polysyllables;
            }
        }

        characterNumber += sentenceNumber - (text.endsWith(".") ? 0 : 1);

        displayTextMetrics(characterNumber, syllableNumber, polysyllables, wordNumber, sentenceNumber);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        String score = scanner.next();
        switch(score) {
            case "ARI" -> displaySimpleResult("Automated Readability Index", getARIScore(characterNumber, wordNumber, sentenceNumber));
            case "FK" -> displaySimpleResult("Flesch–Kincaid readability tests", getFleschKincaidScore(syllableNumber, wordNumber, sentenceNumber));
            case "SMOG" -> displaySimpleResult("Simple Measure of Gobbledygook", getSMOGIndex(polysyllables, sentenceNumber));
            case "CL" -> displaySimpleResult("Coleman–Liau index", getColemanLiauIndex(characterNumber, wordNumber, sentenceNumber));
            case "all" -> {
                int ariAge = displaySimpleResult("Automated Readability Index", getARIScore(characterNumber, wordNumber, sentenceNumber));
                int fkAge = displaySimpleResult("Flesch–Kincaid readability tests", getFleschKincaidScore(syllableNumber, wordNumber, sentenceNumber));
                int smogAge = displaySimpleResult("Simple Measure of Gobbledygook", getSMOGIndex(polysyllables, sentenceNumber));
                int clAge = displaySimpleResult("Coleman–Liau index", getColemanLiauIndex(characterNumber, wordNumber, sentenceNumber));
                double averageAge = (ariAge + fkAge + smogAge + clAge) / 4.0;
                System.out.println();
                System.out.printf("This text should be understood in average by %.2f-year-olds.%n", averageAge);
            }
            default -> throw new RuntimeException("Unknown input");
        }
    }

    private static int displaySimpleResult(String name, double readabilityScore) {
        int age = getAgeUpperBound((int) Math.round(readabilityScore)) + 1;
        displayResult(name, readabilityScore, age);
        return age;
    }

    private static void displayResult(String name, double readabilityScore, int age) {
        System.out.printf("%s: %.2f (about %d-year-olds)%n", name, readabilityScore, age);
    }

    private static void displayTextMetrics(int characters, int syllables, int polysyllables, int words, int sentences) {
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
    }

    private static double getARIScore(int characters, int words, int sentences) {
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }

    private static double getFleschKincaidScore(int syllables, int words, int sentences) {
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    private static double getSMOGIndex(int polysyllables, int sentences) {
        return 1.043 * Math.sqrt(polysyllables * 30 / (double) sentences) + 3.1291;
    }

    private static double getColemanLiauIndex(int characters, int words, int sentences) {
        double L = 100.0 * characters / words;
        double S = 100.0 * sentences / words;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    static int getNumberOfSyllables(String word) {
        final Pattern p = Pattern.compile("[aeiouyAEIOUY]+");
        final Matcher m = p.matcher(word);
        int numberOfSyllables = 0;
        while (m.find()) {
            ++numberOfSyllables;
        }
        if (word.endsWith("e")) numberOfSyllables--;
        if (numberOfSyllables <= 0) numberOfSyllables = 1;
        return numberOfSyllables;
    }

    private static int getAgeUpperBound(int roundedScore) {
        if (roundedScore > 0 && roundedScore < 14) {
            return roundedScore + 5;
        } else if (roundedScore == 14) {
            return 22;
        } else {
            throw new RuntimeException("Unknown age bound");
        }
    }

    private static String readFile(String fileName) throws IOException {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            throw e;
        }
    }
}

package projects.readability;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {


        String text = readFile(args[0]);

        String [] sentences = text.split("[.!?]");
        int sentenceNumber = sentences.length;
        int wordNumber = 0;
        int characterNumber = 0;

        for (int i = 0; i < sentences.length; i++) {
            String [] words = sentences[i].trim().split("\\s");
            wordNumber += words.length;
            for(int j = 0; j < words.length; j++) {
                characterNumber += words[j].length();
            }
        }

        characterNumber += sentenceNumber - (text.endsWith(".") ? 0 : 1);

        double readabilityScore = getReadabilityScore(characterNumber, wordNumber, sentenceNumber);
        int roundedScore = (int) Math.ceil(readabilityScore);
        String age = getYearsOfAge(roundedScore);

        displayResult(characterNumber, wordNumber, sentenceNumber, readabilityScore, age);
    }

    private static String getYearsOfAge(int roundedScore) {
        return switch (roundedScore) {
            case 1 -> "5-6";
            case 2 -> "6-7";
            case 3 -> "7-8";
            case 4 -> "8-9";
            case 5 -> "9-10";
            case 6 -> "10-11";
            case 7 -> "11-12";
            case 8 -> "12-13";
            case 9 -> "13-14";
            case 10 -> "14-15";
            case 11 -> "15-16";
            case 12 -> "16-17";
            case 13 -> "17-18";
            case 14 -> "18-22";
            default -> "unknown";
        };
    }

    private static void displayResult(int characters, int words, int sentences, double score, String yearsOfAge) {
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.printf("The score is: %.2f%n", score);
        System.out.println("This text should be understood by " + yearsOfAge + " year-olds.");
    }

    private static double getReadabilityScore(int characters, int words, int sentences) {
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
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

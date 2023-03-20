package projects.readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String [] sentences = text.split("[.!?]");
        int sentenceNumber = sentences.length;
        int wordNumber = 0;

        for (int i = 0; i < sentences.length; i++) {
            String [] words = sentences[i].trim().split("\\s");
            wordNumber += words.length;
        }

        int wordsPerSentence = wordNumber / sentenceNumber;

        if (wordsPerSentence > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }
}

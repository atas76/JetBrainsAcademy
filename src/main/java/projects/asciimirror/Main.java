package projects.asciimirror;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String input = scanner.nextLine();
        try {
            Path path = Paths.get(input);
            if (path.toFile().isDirectory()) {
                System.out.println("File not found!");
                return;
            }
            List<String> fileLines = new ArrayList<>();
            Files.lines(path).forEachOrdered(fileLines::add);

            Optional<Integer> maxLength = fileLines.stream().map(String::length).max(Integer::compare);
            fileLines.stream().map(line -> padString(line, maxLength.get()))
                    .map(line -> line  + " | " + reverseString(line))
                    .toList().forEach(System.out::println);

        } catch (IOException ex) {
            System.out.println("File not found!");
        }
    }

    private static String reverseString(String str) {
        StringBuilder retVal = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            retVal.append(getSymmetricalChar(str.charAt(i)));
        }
        return retVal.toString();
    }

    private static char getSymmetricalChar(char ch) {
        Map<Character, Character> mirroredChars = Map.ofEntries(
                Map.entry('<', '>'),
                Map.entry('>', '<'),
                Map.entry('[', ']'),
                Map.entry(']', '['),
                Map.entry('{', '}'),
                Map.entry('}', '{'),
                Map.entry('(', ')'),
                Map.entry(')', '('),
                Map.entry('/', '\\'),
                Map.entry('\\', '/')
        );

        return mirroredChars.getOrDefault(ch, ch);
    }

    private static String padString(String str, int desiredLength) {
        return str + " ".repeat(desiredLength - str.length());
    }
}
package projects.asciimirror;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
                    .map(line -> line  + " | " + line)
                    .toList().forEach(System.out::println);

        } catch (IOException ex) {
            System.out.println("File not found!");
        }
    }

    private static String padString(String str, int desiredLength) {
        return str + " ".repeat(desiredLength - str.length());
    }
}
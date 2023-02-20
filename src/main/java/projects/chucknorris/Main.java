package projects.chucknorris;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input string:");
        String input = scanner.nextLine();

        System.out.println("The result:");
        System.out.println(getChuckNorrisUnaryCode(input));
    }

    static String getChuckNorrisUnaryCode(String inputString) {

        String binaryString = getBinaryString(inputString);
        char currentBit = binaryString.charAt(0);
        int currentSequenceCount = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();

        while (index < binaryString.length() - 1) {
            while (binaryString.charAt(index) == currentBit) {
                ++currentSequenceCount;
                ++index;
                if (index > binaryString.length() - 1) break;
            }
            sb.append(encode(currentBit, currentSequenceCount));
            if (index > binaryString.length() - 1) break;
            sb.append(" ");
            currentBit = binaryString.charAt(index);
            currentSequenceCount = 0;
        }
        return sb.toString();
    }

    static String encode(char bit, int sequenceCount) {

        StringBuilder sb = new StringBuilder();

        if (bit == '1') {
            sb.append("0 ");
        } else {
            sb.append("00 ");
        }
        for (int i = 0; i < sequenceCount; i++) {
            sb.append("0");
        }

        return sb.toString();
    }

    private static String getBinaryString(String input) {
        StringBuilder sb = new StringBuilder();
        input.chars().mapToObj(c -> (char) c).forEach(ch ->
                sb.append(String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0')));
        return sb.toString();
    }

    private static void printBinary(String input) {
        input.chars().forEach(ch -> System.out.println((char) ch + " = " +
                String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0')));
    }

    private static void printSpaced(String input) {
        StringBuilder sb = new StringBuilder();
        input.chars().mapToObj(c -> (char) c).forEach(ch -> sb.append(ch).append(" "));
        System.out.println(sb);
    }
}

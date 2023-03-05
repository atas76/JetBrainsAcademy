package projects.chucknorris;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        String operation;

        do {

            System.out.println("Please input operation (encode/decode/exit):");
            operation = scanner.nextLine();
            String input;

            if ("encode".equals(operation)) {
                System.out.println("Input string: ");
                input = scanner.nextLine();
                System.out.println("Encoded string:");
                System.out.println(encode(input));
            } else if ("decode".equals(operation)) {
                System.out.println("Input encoded string:");
                input = scanner.nextLine();
                if (!isValid(input)) {
                    System.out.println("Encoded string is not valid.");
                    continue;
                }
                System.out.println("Decoded string:");
                System.out.println(decode(input));
            } else if(!"exit".equals(operation)) {
                System.out.println("There is no '" + operation + "' operation");
            }
        } while (!"exit".equals(operation));

        System.out.println("Bye!");
    }

    static boolean isValid(String encodedString) {
        return hasValidCharacters(encodedString) &&
                hasValidStructure(encodedString);
    }

    static boolean hasValidStructure(String encodedString) {
        String [] blocks = encodedString.split("\s");
        if (blocks.length % 2 == 1) {
            return false;
        }
        char currentBit;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blocks.length; i += 2) {
            if (!"0".equals(blocks[i]) && !"00".equals(blocks[i])) {
                return false;
            }
            if (blocks[i].length() == 1) {
                currentBit = '1';
            } else {
                currentBit = '0';
            }
            for (int j = 0; j < blocks[i + 1].length(); j++) {
                sb.append(currentBit);
            }
        }

        return sb.length() % 7 == 0;
    }

    static boolean hasValidCharacters(String encodedSting) {
        return encodedSting.chars().allMatch(ch -> ch == '0' || ch == ' ');
    }

    static String decode(String encodedString) {

        StringBuilder decodedStringBuilder = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String [] blocks = encodedString.split("\s");
        char currentBit;
        for (int i = 0; i < blocks.length; i += 2) {
            if (blocks[i].length() == 1) {
                currentBit = '1';
            } else {
                currentBit = '0';
            }
            for (int j = 0; j < blocks[i + 1].length(); j++) {
                sb.append(currentBit);
            }
        }

        for (int i = 0; i < sb.length(); i += 7) {
            String currentBlock = sb.substring(i, i + 7);
            char currentChar = (char) Integer.parseInt(currentBlock, 2);
            decodedStringBuilder.append(currentChar);
        }

        return decodedStringBuilder.toString();
    }

    static String encode(String inputString) {

        String binaryString = getBinaryString(inputString);
        char currentBit = binaryString.charAt(0);
        int currentSequenceCount = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();

        while (true) {
            while (binaryString.charAt(index) == currentBit) {
                ++currentSequenceCount;
                ++index;
                if (index > binaryString.length() - 1) break;
            }
            sb.append(encodeSequence(currentBit, currentSequenceCount));
            if (index > binaryString.length() - 1) break;
            sb.append(" ");
            currentBit = binaryString.charAt(index);
            currentSequenceCount = 0;
        }
        return sb.toString();
    }

    static String encodeSequence(char bit, int sequenceCount) {

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

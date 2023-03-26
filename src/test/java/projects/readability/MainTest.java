package projects.readability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void numberOfSyllablesTest() {
        String sentence1 = "This is the front page of the Simple English Wikipedia.";
        String [] words1 = sentence1.split("\s");
        int numberOfSyllables1 = 0;

        for (int i = 0; i < words1.length; i++) {
            // System.out.println(words1[i] + ": " + Main.getNumberOfSyllables(words1[i]));
            numberOfSyllables1 += Main.getNumberOfSyllables(words1[i]);
        }

        assertEquals(14, numberOfSyllables1);
    }
}
